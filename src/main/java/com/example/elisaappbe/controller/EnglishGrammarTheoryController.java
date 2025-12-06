package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishGrammarRequest;
import com.example.elisaappbe.dto.req.EnglishVocabularyRequest;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.service.englishGrammarTheory.EnglishGrammarTheoryService;
import com.example.elisaappbe.service.englishVocabularyTheory.EnglishVocabularyTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/english-grammar-theories")
public class EnglishGrammarTheoryController {

    @Autowired
    private EnglishGrammarTheoryService grammarTheoryService;

    @GetMapping("/{id}")
    public List<EnglishGrammarTheoryResponse> getVocabularyTheoriesByLesson(@PathVariable Long id) {
        return grammarTheoryService.getGrammarTheoriesByLesson(id);
    }

    @PostMapping(value = "/create/{lessonId}")
    public EnglishGrammarTheoryResponse createGrammarOfLesson(@PathVariable Long lessonId,
                                                                    @RequestBody EnglishGrammarRequest req) {
        return grammarTheoryService.createGrammarOfLesson(lessonId,req);
    }

    @PutMapping(value = "/update/{grammarId}")
    public EnglishGrammarTheoryResponse updateGrammarOfLesson(@PathVariable Long grammarId,
                                                                 @RequestBody EnglishGrammarRequest req) {
        return grammarTheoryService.updateGrammarOfLesson(grammarId,req);
    }

    @DeleteMapping("/delete/{grammarId}")
    public ResponseEntity<Map<String, Object>> deleteGrammarOfLesson(@PathVariable Long grammarId){
        grammarTheoryService.deleteGrammarOfLesson(grammarId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa ngữ pháp thành công");
        response.put("deletedId", grammarId);
        return ResponseEntity.ok(response);
    }

}
