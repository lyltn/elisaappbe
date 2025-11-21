package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.dto.resp.VocabularyTheoryResponse;
import com.example.elisaappbe.service.englishVocabularyTheory.EnglishVocabularyTheoryService;
import com.example.elisaappbe.service.vocabularyTheory.VocabularyTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/english-vocabulary-theories")
public class EnglishVocabularyTheoryController {

    @Autowired
    private EnglishVocabularyTheoryService vocabularyTheoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Page<EnglishVocabularyTheoryResponse>> getVocabularyTheoriesByLesson(@PathVariable Long id,
                                                                                               @PageableDefault(size = 10) Pageable pageable) {

        Page<EnglishVocabularyTheoryResponse> responsePage =
                vocabularyTheoryService.getVocabularyTheoriesByLesson(id, pageable);
        return ResponseEntity.ok(responsePage);
    }
}
