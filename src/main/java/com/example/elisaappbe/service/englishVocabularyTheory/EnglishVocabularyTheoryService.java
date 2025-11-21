package com.example.elisaappbe.service.englishVocabularyTheory;

import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnglishVocabularyTheoryService {
    List<EnglishVocabularyTheoryResponse> getAllVocabularyTheories();
    Page<EnglishVocabularyTheoryResponse> getVocabularyTheoriesByLesson(Long id, Pageable pageable);

}
