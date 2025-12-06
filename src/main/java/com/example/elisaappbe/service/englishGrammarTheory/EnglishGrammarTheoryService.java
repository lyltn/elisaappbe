package com.example.elisaappbe.service.englishGrammarTheory;

import com.example.elisaappbe.dto.req.EnglishGrammarRequest;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;

import java.util.List;

public interface EnglishGrammarTheoryService {
    List<EnglishGrammarTheoryResponse> getGrammarTheoriesByLesson(Long id);
    EnglishGrammarTheoryResponse createGrammarOfLesson(Long lessonId, EnglishGrammarRequest req);
    EnglishGrammarTheoryResponse updateGrammarOfLesson(Long grammarId, EnglishGrammarRequest req);
    void deleteGrammarOfLesson(Long grammarId);
}
