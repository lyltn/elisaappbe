package com.example.elisaappbe.service.englishGrammarTheory;

import com.example.elisaappbe.dto.req.EnglishGrammarRequest;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.model.EnglishGrammarTheory;
import com.example.elisaappbe.model.EnglishLesson;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import com.example.elisaappbe.repository.EnglishGrammarTheoryRepository;
import com.example.elisaappbe.repository.EnglishLessonRepository;
import com.example.elisaappbe.repository.EnglishVocabularyTheoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnglishGrammarTheoryServiceImpl implements EnglishGrammarTheoryService{

    @Autowired
    private EnglishGrammarTheoryRepository grammarRepository;

    @Autowired
    private EnglishLessonRepository lessonRepository;


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

    @Override
    public EnglishGrammarTheoryResponse createGrammarOfLesson(Long lessonId, EnglishGrammarRequest req) {
        EnglishGrammarTheory newGrammar = new EnglishGrammarTheory();
        EnglishLesson getLesson = lessonRepository.findByLessonId(lessonId);
        newGrammar.setEnglishLesson(getLesson);
        newGrammar.setGrammarTitle(req.getGrammarTitle());
        newGrammar.setGrammarContent(req.getGrammarContent());
        newGrammar.setGrammarUsage(req.getGrammarUsage());
        newGrammar.setGrammarExample(req.getGrammarExample());
        newGrammar = grammarRepository.save(newGrammar);
        return toResponse(newGrammar);
    }

    @Override
    public EnglishGrammarTheoryResponse updateGrammarOfLesson(Long grammarId, EnglishGrammarRequest req) {
        Optional<EnglishGrammarTheory> getGrammar = grammarRepository.findById(grammarId);
        if(getGrammar.isPresent()){
            EnglishGrammarTheory updateGrammar = getGrammar.get();
            updateGrammar.setGrammarTitle(req.getGrammarTitle());
            updateGrammar.setGrammarContent(req.getGrammarContent());
            updateGrammar.setGrammarUsage(req.getGrammarUsage());
            updateGrammar.setGrammarExample(req.getGrammarExample());
            updateGrammar = grammarRepository.save(updateGrammar);
            return toResponse(updateGrammar);
        }
        return null;
    }

    @Override
    public void deleteGrammarOfLesson(Long grammarId) {
        Optional<EnglishGrammarTheory> getGrammar = grammarRepository.findById(grammarId);
        if(getGrammar.isPresent()){
            grammarRepository.deleteById(grammarId);
        }
    }
}
