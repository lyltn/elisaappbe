package com.example.elisaappbe.service.englishVocabularyTheory;

import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.dto.resp.VocabularyTheoryResponse;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import com.example.elisaappbe.model.VocabularyTheory;
import com.example.elisaappbe.repository.EnglishVocabularyTheoryRepository;
import com.example.elisaappbe.repository.VocabularyTheoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnglishVocabularyTheoryServiceImpl implements EnglishVocabularyTheoryService {

    @Autowired
    private EnglishVocabularyTheoryRepository vocabularyRepository;

    private EnglishVocabularyTheoryResponse toResponse(EnglishVocabularyTheory vocab) {
        EnglishVocabularyTheoryResponse resp = new EnglishVocabularyTheoryResponse();
        resp.setVocabId(vocab.getVocabId());
        resp.setWord(vocab.getWord());
        resp.setMeaning(vocab.getMeaning());
        resp.setType(vocab.getType());
        resp.setExample(vocab.getExample());
        resp.setImage(vocab.getImage());
        return resp;
    }

    @Override
    public List<EnglishVocabularyTheoryResponse> getAllVocabularyTheories() {
        return vocabularyRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EnglishVocabularyTheoryResponse> getVocabularyTheoriesByLesson(Long id, Pageable pageable) {
        Page<EnglishVocabularyTheory> vocabularyPage =
                vocabularyRepository.findByEnglishLesson_LessonId(id, pageable);
        return vocabularyPage.map(this::toResponse);
    }
}
