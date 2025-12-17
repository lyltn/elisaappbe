package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishChatbotRequest;
import com.example.elisaappbe.dto.resp.EnglishChatbotResponse;
import com.example.elisaappbe.dto.resp.EnglishCheckMessageResponse;
import com.example.elisaappbe.service.englishChatbot.GroqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class EnglishChatbotController {

    @Autowired
    private GroqService groqService;

    @PostMapping(value = "/voice", consumes = "multipart/form-data")
    public ResponseEntity<EnglishChatbotResponse> chatWithVoice(
            @RequestParam("audio") MultipartFile audioFile,
            @RequestParam("topic") String topic,
            @RequestParam("level") String level
            ) {

        try {
            // Bước 1: Speech to Text
            String userText = groqService.transcribeAudio(audioFile);

            if (userText == null || userText.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Bước 2: Chat với AI
            EnglishChatbotResponse aiResponse = groqService.getChatResponse(userText, topic, level);

            return ResponseEntity.ok(aiResponse);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/text")
    public ResponseEntity<EnglishChatbotResponse> chatWithText(@RequestBody EnglishChatbotRequest req) {
        EnglishChatbotResponse aiResponse = groqService.getChatResponse(req.getMessage(), req.getTopic(), req.getLevel());
        return ResponseEntity.ok(aiResponse);

    }

    @PostMapping("/grammar-reply")
    public ResponseEntity<EnglishCheckMessageResponse> checkGrammar(@RequestBody EnglishChatbotRequest req) {

        if (req.getMessage() == null || req.getMessage().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String level = (req.getLevel() != null) ? req.getLevel() : "A1";
        String topic = (req.getTopic() != null) ? req.getTopic() : "General";

        EnglishCheckMessageResponse response = groqService.checkGrammarAndReply(
                req.getMessage(),
                topic,
                level
        );

        return ResponseEntity.ok(response);
    }

}
