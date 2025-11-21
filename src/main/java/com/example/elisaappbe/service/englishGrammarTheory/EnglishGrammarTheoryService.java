package com.example.elisaappbe.service.englishGrammarTheory;

import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;

import java.util.List;

public interface EnglishGrammarTheoryService {
    List<EnglishGrammarTheoryResponse> getGrammarTheoriesByLesson(Long id);
}
