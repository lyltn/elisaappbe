package com.example.elisaappbe.controller;


import com.example.elisaappbe.dto.req.EnglishGrammarRequest;
import com.example.elisaappbe.dto.req.EnglishMultipleChoiceRequest;
import com.example.elisaappbe.dto.req.EnglishSentenceRewritingRequest;
import com.example.elisaappbe.dto.resp.EnglishExerciseResponse;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishMultipleChoiceResponse;
import com.example.elisaappbe.dto.resp.EnglishSentenceRewritingResponse;
import com.example.elisaappbe.service.englishExercise.EnglishExerciseService;
import com.example.elisaappbe.service.englishGrammarTheory.EnglishGrammarTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/english-exercise")
public class EnglishExerciseController {

    @Autowired
    private EnglishExerciseService exerciseService;

    @GetMapping("/{id}")
    public EnglishExerciseResponse getVocabularyTheoriesByLesson(@PathVariable Long id) {
        return exerciseService.getExerciseByLesson(id);
    }

    @PostMapping("/create/multiple/{lessonId}")
    public EnglishMultipleChoiceResponse createMultipleQuestion(@PathVariable Long lessonId, @RequestBody EnglishMultipleChoiceRequest req){
        return exerciseService.createMultipleChoiceQuestion(lessonId,req);
    }

    @PutMapping("/update/multiple/{questionId}")
    public EnglishMultipleChoiceResponse updateMultipleQuestion(@PathVariable Long questionId, @RequestBody EnglishMultipleChoiceRequest req){
        return exerciseService.updateMultipleChoiceQuestion(questionId,req);
    }

    @DeleteMapping("/delete/multiple/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteMultipleQuestion(@PathVariable Long questionId){
        exerciseService.deleteMultipleChoiceQuestion(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa câu trắc nghiệm thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/sentence-rewriting/{lessonId}")
    public EnglishSentenceRewritingResponse createSentenceRewritingQuestion(@PathVariable Long lessonId, @RequestBody EnglishSentenceRewritingRequest req){
        return exerciseService.createSentenceRewritingQuestion(lessonId,req);
    }

    @PutMapping("/update/sentence-rewriting/{questionId}")
    public EnglishSentenceRewritingResponse updateSentenceRewritingQuestion(@PathVariable Long questionId, @RequestBody EnglishSentenceRewritingRequest req){
        return exerciseService.updateSentenceRewritingQuestion(questionId,req);
    }

    @DeleteMapping("/delete/sentence-rewriting/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteSentenceRewritingQuestion(@PathVariable Long questionId){
        exerciseService.deleteSentenceRewritingQuestion(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa ghép câu thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }
}
