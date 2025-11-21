package com.example.elisaappbe.service.englishUserProgress;

import com.example.elisaappbe.dto.req.EnglishUserProgressRequest;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;
import com.example.elisaappbe.dto.resp.EnglishVocabularyTheoryResponse;
import com.example.elisaappbe.model.EnglishUserProgress;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import com.example.elisaappbe.model.User;
import com.example.elisaappbe.repository.EnglishLessonRepository;
import com.example.elisaappbe.repository.EnglishUserProgressRepository;
import com.example.elisaappbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnglishUserProgressServiceImpl implements EnglishUserProgressService{

    @Autowired
    private EnglishUserProgressRepository userProgress;

    @Autowired
    private EnglishLessonRepository lessonRepository;

    @Autowired
    private UserRepository userRepository;


    private EnglishUserProgressResponse toResponse(EnglishUserProgress vocab) {
        EnglishUserProgressResponse resp = new EnglishUserProgressResponse();
        resp.setProgressId(vocab.getProgressId());
        resp.setLessonId(vocab.getEnglishLesson().getLessonId());
        resp.setSection(vocab.getSection());
        resp.setLastAccessed(vocab.getLastAccessed());
        return resp;
    }

    @Override
    public EnglishUserProgressResponse getUserProgress(long userId) {
        Optional<EnglishUserProgress> result = userProgress.findByUser_UserId(userId);
            return result.map(this::toResponse).orElse(null);
    }

    @Override
    public EnglishUserProgressResponse updateProgress(EnglishUserProgressRequest res) {
        Optional<EnglishUserProgress> result = userProgress.findByUser_UserId(res.getUserId());
        if(result.isPresent()){
            EnglishUserProgress newProgress = result.get();
            newProgress.setEnglishLesson(lessonRepository.findByLessonId(res.getLessonId()));
            newProgress.setSection(res.getSection());
            newProgress.setLastAccessed(LocalDateTime.now());

            EnglishUserProgress savedProgress = userProgress.save(newProgress);
            return new EnglishUserProgressResponse(
                    savedProgress.getProgressId(),
                    savedProgress.getEnglishLesson().getLessonId(),
                    savedProgress.getSection(),
                    savedProgress.getLastAccessed()
            );
        }
        return null;
    }

    @Override
    public EnglishUserProgressResponse createUserProgress(long userId) {
        EnglishUserProgress newUser = new EnglishUserProgress();
        Optional<User> getUser =  userRepository.findById(userId);
        if(getUser.isPresent()){
            newUser.setUser(getUser.get());
            newUser.setEnglishLesson(lessonRepository.findByLessonId(1));
            newUser.setLastAccessed(LocalDateTime.now());
            newUser.setSection(1L);
            EnglishUserProgress savedProgress =  userProgress.save(newUser);
            return new EnglishUserProgressResponse(
                    savedProgress.getProgressId(),
                    savedProgress.getEnglishLesson().getLessonId(),
                    savedProgress.getSection(),
                    savedProgress.getLastAccessed()
            );
        }
        return null;
    }
}
