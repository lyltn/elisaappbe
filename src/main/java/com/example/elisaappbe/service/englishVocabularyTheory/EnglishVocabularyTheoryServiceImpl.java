package com.example.elisaappbe.service.englishVocabularyTheory;

import com.example.elisaappbe.dto.req.EnglishVocabularyRequest;
import com.example.elisaappbe.dto.resp.EnglishIPAResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.dto.resp.UserResponse;
import com.example.elisaappbe.dto.resp.VocabularyTheoryResponse;
import com.example.elisaappbe.model.EnglishLesson;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import com.example.elisaappbe.model.VocabularyTheory;
import com.example.elisaappbe.repository.EnglishLessonRepository;
import com.example.elisaappbe.repository.EnglishVocabularyTheoryRepository;
import com.example.elisaappbe.repository.VocabularyTheoryRepository;
import com.example.elisaappbe.service.cloud.CloudinaryService;
import com.example.elisaappbe.service.englishChatbot.GroqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnglishVocabularyTheoryServiceImpl implements EnglishVocabularyTheoryService {

    @Autowired
    private EnglishVocabularyTheoryRepository vocabularyRepository;

    @Autowired
    private EnglishLessonRepository lessonRepository;

    @Autowired
    private CloudinaryService cloudService;

    @Autowired
    private GroqService groqService;

    private EnglishVocabularyTheoryResponse toResponse(EnglishVocabularyTheory vocab) {
        EnglishVocabularyTheoryResponse resp = new EnglishVocabularyTheoryResponse();
        resp.setVocabId(vocab.getVocabId());
        resp.setWord(vocab.getWord());
        resp.setMeaning(vocab.getMeaning());
        resp.setType(vocab.getType());
        resp.setIpa(vocab.getIpa());
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

    @Override
    public EnglishVocabularyTheoryResponse creteaVocabularyOfLesson(Long lessonId, EnglishVocabularyRequest req) throws IOException {
        EnglishVocabularyTheory createVocab = new EnglishVocabularyTheory();
        EnglishLesson getLesson = lessonRepository.findByLessonId(lessonId);
        String publicId = lessonId +"_"+req.getWord();
        String urlImg = cloudService.uploadFile(req.getFile(),publicId);
        createVocab.setEnglishLesson(getLesson);
        createVocab.setWord(req.getWord());
        createVocab.setMeaning(req.getMeaning());
        createVocab.setType(req.getType());
        createVocab.setIpa(req.getIpa());
        createVocab.setExample(req.getExample());
        createVocab.setImage(urlImg);

        createVocab = vocabularyRepository.save(createVocab);

        return toResponse(createVocab);
    }

    @Override
    public EnglishVocabularyTheoryResponse updateVocabularyOfLesson(Long vocabId, EnglishVocabularyRequest req) throws IOException {
        Optional<EnglishVocabularyTheory> getVocab = vocabularyRepository.findById(vocabId);
        if(getVocab.isPresent()){
            EnglishVocabularyTheory updateVocab = getVocab.get();
            updateVocab.setWord(req.getWord());
            updateVocab.setMeaning(req.getMeaning());
            updateVocab.setType(req.getType());
            updateVocab.setIpa(req.getIpa());
            updateVocab.setExample(req.getExample());
            if(req.getFile() != null){
                if(!req.getFile().isEmpty()){
                    String publicId = updateVocab.getEnglishLesson().getLessonId() +"_"+req.getWord();
                    String urlImg = cloudService.uploadFile(req.getFile(),publicId);
                    updateVocab.setImage(urlImg);
                }
            }
            updateVocab = vocabularyRepository.save(updateVocab);
            return toResponse(updateVocab);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteVocabularyOfLesson(Long vocabId) {
        Optional<EnglishVocabularyTheory> getVocab = vocabularyRepository.findById(vocabId);
        if(getVocab.isPresent()){
            vocabularyRepository.deleteById(vocabId);
        }
    }

    @Override
    public EnglishIPAResponse getIPAVocabulary(String vocab) {
        return groqService.getIPAVocabulary(vocab) ;
    }
}
