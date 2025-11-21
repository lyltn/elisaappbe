package com.example.elisaappbe.service.englishGrammarTheory;

import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.model.EnglishGrammarTheory;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import com.example.elisaappbe.repository.EnglishGrammarTheoryRepository;
import com.example.elisaappbe.repository.EnglishVocabularyTheoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnglishGrammarTheoryServiceImpl implements EnglishGrammarTheoryService{

    @Autowired
    private EnglishGrammarTheoryRepository grammarRepository;


    private EnglishGrammarTheoryResponse toResponse(EnglishGrammarTheory vocab) {
        EnglishGrammarTheoryResponse resp = new EnglishGrammarTheoryResponse();
        resp.setGrammarId(vocab.getGrammarId());
        resp.setGrammarTitle(vocab.getGrammarTitle());
        resp.setGrammarContent(vocab.getGrammarContent());
        resp.setGrammarUsage(vocab.getGrammarUsage());
        resp.setGrammarExample(vocab.getGrammarExample());
        return resp;
    }

    @Override
    public List<EnglishGrammarTheoryResponse> getGrammarTheoriesByLesson(Long id) {
        return grammarRepository.findByEnglishLesson_LessonId(id).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
