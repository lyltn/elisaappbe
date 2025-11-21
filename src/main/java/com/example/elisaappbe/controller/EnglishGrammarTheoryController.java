package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.service.englishGrammarTheory.EnglishGrammarTheoryService;
import com.example.elisaappbe.service.englishVocabularyTheory.EnglishVocabularyTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/english-grammar-theories")
public class EnglishGrammarTheoryController {

    @Autowired
    private EnglishGrammarTheoryService grammarTheoryService;

    @GetMapping("/{id}")
    public List<EnglishGrammarTheoryResponse> getVocabularyTheoriesByLesson(@PathVariable Long id) {
        return grammarTheoryService.getGrammarTheoriesByLesson(id);
    }

}
