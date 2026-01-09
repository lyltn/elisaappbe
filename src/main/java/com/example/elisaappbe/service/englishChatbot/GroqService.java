package com.example.elisaappbe.service.englishChatbot;

import com.example.elisaappbe.dto.resp.EnglishChatbotResponse;
import com.example.elisaappbe.dto.resp.EnglishCheckMessageResponse;
import com.example.elisaappbe.dto.resp.EnglishIPAResponse;
import com.example.elisaappbe.dto.resp.EnglishPronunciationScoreResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.api.url.chat}")
    private String chatUrl;

    @Value("${groq.api.url.audio}")
    private String audioUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1. Chức năng Speech to Text (Whisper)
    public String transcribeAudio(MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Chuyển MultipartFile thành File tạm để gửi đi
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(convFile));
        body.add("model", "whisper-large-v3"); // Model bạn yêu cầu
        body.add("response_format", "json");

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(audioUrl, requestEntity, Map.class);
            return (String) response.getBody().get("text");
        } finally {
            convFile.delete(); // Xóa file tạm
        }
    }

    // 2. Chức năng Chat AI (Llama 3.1)
    public EnglishChatbotResponse getChatResponse(String userMessage, String topic, String level) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // --- CẬP NHẬT SYSTEM PROMPT (Thêm định danh Elisa) ---
        String systemPrompt = String.format(
                "PRIME DIRECTIVE - STRICT LANGUAGE GATEKEEPER:\n" +
                        "1. **STEP 1: DISCRIMINATE LANGUAGE (CRITICAL)**.\n" +
                        "   - Analyze the input immediately. Distinguish between English and Vietnamese greetings.\n" +
                        "   - **ALLOW (Valid English)**: Inputs like 'Hello', 'Hi', 'Good morning', 'Who are you?', 'What is your name?' -> **PROCEED to Step 3**.\n" +
                        "   - **BLOCK (Vietnamese)**: Inputs like 'Xin chào', 'Chào', 'Lô', 'Tôi là ai', 'Giúp với', 'Bạn tên gì' -> **GO to Step 2**.\n\n" +

                        "2. **STEP 2: IF VIETNAMESE IS DETECTED**:\n" +
                        "   - **STOP processing intent**. Do NOT answer the question.\n" +
                        "   - **ACTION**: Return the refusal JSON asking for English (e.g., 'Please use English').\n\n" +

                        "3. **STEP 3: IF ENGLISH (Valid)**:\n" +
                        "   - **ACTION**: Act as Elisa. Answer the user enthusiastically.\n" +
                        "   - **BROKEN ENGLISH**: If input is 'what name you' or 'hello me', GUESS intent and answer.\n\n" +

                        "ROLE & IDENTITY (Only applies if input is English):\n" +
                        "You are 'Elisa', an English communication learning assistant for the Elisa system. Your name is Elisa. " +
                        "You are helpful, friendly, and intuitive.\n\n" +

                        "CONTEXT:\n" +
                        "Topic: %s. User Level: %s.\n\n" +

                        "INSTRUCTIONS FOR BROKEN ENGLISH (Only applies if input is English):\n" +
                        "1. If the input is English but has grammar errors/typos, DO NOT say 'I don't understand'.\n" +
                        "2. ACTIVELY GUESS the intent and keep the conversation flowing.\n\n" +

                        "RESPONSE FORMAT (REQUIRED FOR ALL RESPONSES INCLUDING REFUSALS):\n" +
                        "1. Answer (or the refusal message) in English (adjust vocabulary to level %s).\n" +
                        "2. Provide a natural Vietnamese translation of that English response.\n" +
                        "3. Return strictly a JSON object with keys: \"english_reply\", \"vietnamese_translation\".",
                topic, level, level
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.1-8b-instant");
        body.put("temperature", 0.2);
        body.put("top_p", 0.8);

        // Ép trả về JSON
        Map<String, String> responseFormat = new HashMap<>();
        responseFormat.put("type", "json_object");
        body.put("response_format", responseFormat);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messages.add(Map.of("role", "user", "content", userMessage));

        body.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(chatUrl, requestEntity, Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String jsonContent = (String) message.get("content");

            JsonNode jsonNode = objectMapper.readTree(jsonContent);

            EnglishChatbotResponse result = new EnglishChatbotResponse();
            result.setUserText(userMessage);
            result.setAnswerChatbot(jsonNode.get("english_reply").asText());
            result.setAnswerVietnamese(jsonNode.get("vietnamese_translation").asText());

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new EnglishChatbotResponse();
        }
    }

    public EnglishCheckMessageResponse checkGrammarAndReply(String userMessage, String topic, String level) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 1. Cấu trúc lại System Prompt để ép AI trả về JSON
        String systemPrompt = String.format(
                "You are a strict Grammar Checker for English learners. Analyze the user's sentence strictly based on the text provided.\n" +
                        "Topic Context: %s (Only use this to understand vocabulary).\n" +
                        "Target Level: %s\n\n" +

                        "CRITICAL RULES:\n" +
                        "1. LANGUAGE DETECTION: \n" +
                        "   - If the input contains English words but in the wrong order (e.g., 'what name is your?'), treat it as INCORRECT ENGLISH. Give a low score, provide the correct version in 'editedSentence', and explain in 'hintText'.\n" +
                        "   - ONLY trigger Rule 2 if the input is primarily in Vietnamese or another non-English language.\n" +
                        "2. NON-ENGLISH INPUT: If the user writes in Vietnamese:\n" +
                        "   - Set 'score' to 0.0.\n" +
                        "   - Set 'editedSentence' to the English translation of that sentence.\n" +
                        "   - Set 'hintText' exactly to: \"Vui lòng nhập nội dung hội thoại bằng tiếng Anh để tôi có thể kiểm tra năng lực ngữ pháp của bạn.\"\n" +
                        "   - STOP processing further.\n" +
                        "3. CHECK ONLY THE INPUT: Do not add unnecessary info (e.g., 'Hello' stays 'Hello', not 'Hello, how are you?').\n" +
                        "4. PUNCTUATION: Accept 'Hello.' and 'Hello,' as correct.\n" +
                        "5. SCORING: 10.0 for correct grammar. Lower the score based on the severity of grammatical/word order errors.\n" +
                        "6. NO HALLUCINATION: Do not add words the user didn't use unless correcting grammar or translating (Rule 2).\n\n" +

                        "Output Requirement: Return a valid JSON object with exactly these fields:\n" +
                        "- score: Float (0.0 to 10.0).\n" +
                        "- editedSentence: The corrected English version or the English translation (if Rule 2 is triggered).\n" +
                        "- hintText: A short explanation (max 30 words) IN VIETNAMESE. If the sentence is correct, return: \"Câu của bạn hoàn toàn chính xác!\".\n" +
                        "Input sentence: \"%s\"",
                topic, level, userMessage
        );

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.1-8b-instant");
        body.put("temperature", 0.1);
        body.put("top_p", 0.5);

        // 2. QUAN TRỌNG: Ép kiểu trả về là JSON Object để tránh lỗi parsing
        // Lưu ý: Groq/OpenAI hỗ trợ tham số này.
        Map<String, String> responseFormat = new HashMap<>();
        responseFormat.put("type", "json_object");
        body.put("response_format", responseFormat);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messages.add(Map.of("role", "user", "content", "Analyze my sentence: " + userMessage));

        body.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(chatUrl, requestEntity, Map.class);

            // 3. Lấy nội dung JSON từ response
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String jsonContent = (String) message.get("content");

            // 4. Parse chuỗi JSON thành Object Java
            EnglishCheckMessageResponse result = objectMapper.readValue(jsonContent, EnglishCheckMessageResponse.class);

            // Gán lại câu gốc từ input (để đảm bảo chính xác tuyệt đối những gì user nhập)
            result.setOriginalSentence(userMessage);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi (Fallback)
            EnglishCheckMessageResponse errorResponse = new EnglishCheckMessageResponse();
            errorResponse.setOriginalSentence(userMessage);
            errorResponse.setScore(0.0f);
            errorResponse.setEditedSentence("Server update in progress. Please try again later.");
            errorResponse.setHintText("Server is busy, please try again.");
            return errorResponse;
        }
    }

    public EnglishIPAResponse getIPAVocabulary(String vocab) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Prompt được tinh chỉnh để cực kỳ ngắn gọn và ép kết quả IPA
        String systemPrompt = "You are a specialized linguistic assistant. " +
                "Your ONLY task is to provide the IPA (International Phonetic Alphabet) transcription for English words. " +
                "Return the result in a valid JSON format with the fields 'word' and 'ipa'. " +
                "Use the standard American English pronunciation (General American). " +
                "Do not provide any explanations, notes, or extra text.";

        Map<String, Object> body = new HashMap<>();
        body.put("model", "llama-3.1-8b-instant");
        body.put("temperature", 0.0); // Đưa về 0 để kết quả luôn đồng nhất (Deterministic)

        // Ép kiểu trả về JSON
        Map<String, String> responseFormat = new HashMap<>();
        responseFormat.put("type", "json_object");
        body.put("response_format", responseFormat);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messages.add(Map.of("role", "user", "content", "Word: " + vocab));

        body.put("messages", messages);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(chatUrl, requestEntity, Map.class);

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String jsonContent = (String) message.get("content");

            // Parse JSON trả về class IpaResponse
            return objectMapper.readValue(jsonContent, EnglishIPAResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            EnglishIPAResponse error = new EnglishIPAResponse();
            error.setWord(vocab);
            error.setIpa("");
            return error;
        }
    }


    public EnglishPronunciationScoreResponse checkPronunciation(MultipartFile audioFile, String targetWord) throws IOException {
        // 1. Chuyển đổi âm thanh thành văn bản qua mô hình whisper-large-v3
        String recognizedText = transcribeAudio(audioFile);

        if (recognizedText == null || recognizedText.isEmpty()) {
            return new EnglishPronunciationScoreResponse(targetWord, 0.0f, "Tôi không nghe thấy gì, bạn hãy thử nói lại nhé!");
        }

        // 2. Tính điểm bằng thuật toán Levenshtein
        float score = calculateLevenshteinScore(recognizedText, targetWord);

        // 3. Tạo lời khuyên (hint) dựa trên thang điểm
        String hint;
        if (score >= 90) {
            hint = "Xuất sắc! Bạn phát âm gần như người bản xứ.";
        } else if (score >= 70) {
            hint = "Rất tốt! Một vài âm tiết cần rõ ràng hơn một chút.";
        } else if (score >= 50) {
            hint = "Khá ổn, nhưng bạn cần chú ý kỹ hơn về các âm đuôi.";
        } else {
            hint = "Chưa chính xác lắm. Hãy nghe lại phát âm mẫu và thử lại nhé!";
        }

        // 4. Trả về kết quả khớp với cấu trúc class của bạn
        return new EnglishPronunciationScoreResponse(
                recognizedText, // Trả về từ AI nghe được để học sinh đối chiếu
                score,
                hint
        );
    }

    /**
     * Thuật toán tính độ tương đồng văn bản Levenshtein trả về giá trị float (0-100)
     */
    private int calculateLevenshteinScore(String recognized, String target) {
        // Làm sạch chuỗi: viết thường, bỏ ký tự đặc biệt
        String s1 = recognized.toLowerCase().replaceAll("[^a-z0-9]", "").trim();
        String s2 = target.toLowerCase().replaceAll("[^a-z0-9]", "").trim();

        if (s1.equals(s2)) return 100;
        if (s1.isEmpty() || s2.isEmpty()) return 0;

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        }

        int distance = costs[s2.length()];
        int maxLength = Math.max(s1.length(), s2.length());

        // Tính toán score kiểu float để giữ độ chính xác trước khi làm tròn
        float rawScore = ((float) (maxLength - distance) / maxLength) * 100;

        // Làm tròn thành số nguyên và đảm bảo không nhỏ hơn 0
        return Math.max(0, Math.round(rawScore));
    }

}