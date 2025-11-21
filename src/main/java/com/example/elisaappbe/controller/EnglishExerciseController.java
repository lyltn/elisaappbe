package com.example.elisaappbe.controller;


import com.example.elisaappbe.dto.resp.EnglishExerciseResponse;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.service.englishExercise.EnglishExerciseService;
import com.example.elisaappbe.service.englishGrammarTheory.EnglishGrammarTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/english-exercise")
public class EnglishExerciseController {

    @Autowired
    private EnglishExerciseService exerciseService;

    @GetMapping("/{id}")
    public EnglishExerciseResponse getVocabularyTheoriesByLesson(@PathVariable Long id) {
        return exerciseService.getExerciseByLesson(id);
    }
}
