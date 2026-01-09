package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishVocabularyRequest;
import com.example.elisaappbe.dto.resp.*;
import com.example.elisaappbe.service.englishChatbot.GroqService;
import com.example.elisaappbe.service.englishVocabularyTheory.EnglishVocabularyTheoryService;
import com.example.elisaappbe.service.vocabularyTheory.VocabularyTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/english-vocabulary-theories")
public class EnglishVocabularyTheoryController {

    @Autowired
    private EnglishVocabularyTheoryService vocabularyTheoryService;

    @Autowired
    private GroqService groqService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<EnglishVocabularyTheoryResponse>> getVocabularyTheoriesByLesson(@PathVariable Long id,
                                                                                               @PageableDefault(size = 10) Pageable pageable) {

        Page<EnglishVocabularyTheoryResponse> responsePage =
                vocabularyTheoryService.getVocabularyTheoriesByLesson(id, pageable);
        return ResponseEntity.ok(responsePage);
    }

    @PostMapping(value = "/create/{lessonId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public EnglishVocabularyTheoryResponse createVocabularyOfLesson(@PathVariable Long lessonId,
                                                                    @ModelAttribute EnglishVocabularyRequest req) throws IOException {
        return vocabularyTheoryService.creteaVocabularyOfLesson(lessonId,req);
    }

    @PutMapping(value = "/update/{vocabId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public EnglishVocabularyTheoryResponse updateVocabularyOfLesson(@PathVariable Long vocabId,
                                                                    @ModelAttribute EnglishVocabularyRequest req) throws IOException {
        return vocabularyTheoryService.updateVocabularyOfLesson(vocabId,req);
    }

    @DeleteMapping("/delete/{vocabId}")
    public ResponseEntity<Map<String, Object>> deleteVocabularyOfLesson(@PathVariable Long vocabId){
        vocabularyTheoryService.deleteVocabularyOfLesson(vocabId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa từ vựng thành công");
        response.put("deletedId", vocabId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ipa/{vocab}")
    public ResponseEntity<EnglishIPAResponse> getIPAVovabulary(@PathVariable String vocab) {
        return ResponseEntity.ok(vocabularyTheoryService.getIPAVocabulary(vocab));
    }

    @PostMapping(value = "/pronunciation-score", consumes = "multipart/form-data")
    public ResponseEntity<EnglishPronunciationScoreResponse> chatWithVoice(
            @RequestParam("audio") MultipartFile audioFile,
            @RequestParam("word") String word
    ) {
        try {
            EnglishPronunciationScoreResponse response = groqService.checkPronunciation(audioFile, word);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }



}
