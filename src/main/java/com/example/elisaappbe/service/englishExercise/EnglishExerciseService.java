package com.example.elisaappbe.service.englishExercise;

import com.example.elisaappbe.dto.req.EnglishMultipleChoiceRequest;
import com.example.elisaappbe.dto.req.EnglishSentenceRewritingRequest;
import com.example.elisaappbe.dto.resp.*;

import java.util.List;

public interface EnglishExerciseService {
    EnglishExerciseResponse getExerciseByLesson(long id);
    EnglishMultipleChoiceResponse createMultipleChoiceQuestion(Long lessonId, EnglishMultipleChoiceRequest req);
    EnglishMultipleChoiceResponse updateMultipleChoiceQuestion(Long questionId, EnglishMultipleChoiceRequest req);
    void deleteMultipleChoiceQuestion(Long questionId);

    EnglishSentenceRewritingResponse createSentenceRewritingQuestion(Long lessonId, EnglishSentenceRewritingRequest req);
    EnglishSentenceRewritingResponse updateSentenceRewritingQuestion(Long questionId, EnglishSentenceRewritingRequest req);
    void deleteSentenceRewritingQuestion(Long questionId);

    List<EnglishMultipleChoiceResponse> getMultipleChoiceForChallenge(Long lessonId);
    List<EnglishSentenceRewritingResponse> getSentenceRewritingForChallenge(Long lessonId);
    List<EnglishListeningDictationResponse>  getListeningDictationForChallenge(Long lessonId);
    List<EnglishClozeExerciseResponse> getClozeForChallenge(Long lessonId);
    List<EnglishOrderingExerciseResponse> getOrderingForChallenge(Long lessonId);
}
