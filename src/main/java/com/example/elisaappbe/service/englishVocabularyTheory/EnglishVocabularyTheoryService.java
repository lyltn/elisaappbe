package com.example.elisaappbe.service.englishVocabularyTheory;

import com.example.elisaappbe.dto.req.EnglishVocabularyRequest;
import com.example.elisaappbe.dto.resp.EnglishIPAResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface EnglishVocabularyTheoryService {
    List<EnglishVocabularyTheoryResponse> getAllVocabularyTheories();
    Page<EnglishVocabularyTheoryResponse> getVocabularyTheoriesByLesson(Long id, Pageable pageable);
    EnglishVocabularyTheoryResponse creteaVocabularyOfLesson(Long lessonId, EnglishVocabularyRequest req) throws IOException;
    EnglishVocabularyTheoryResponse updateVocabularyOfLesson(Long vocabId, EnglishVocabularyRequest req) throws IOException;
    void deleteVocabularyOfLesson(Long vocabId);
    EnglishIPAResponse getIPAVocabulary(String vocab);


}
