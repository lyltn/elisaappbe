package com.example.elisaappbe.service.englishExercise;

import com.example.elisaappbe.dto.resp.EnglishExerciseResponse;
import com.example.elisaappbe.dto.resp.EnglishGrammarTheoryResponse;
import com.example.elisaappbe.dto.resp.EnglishMultipleChoiceResponse;
import com.example.elisaappbe.dto.resp.EnglishSentenceRewritingResponse;
import com.example.elisaappbe.model.EnglishExercise;
import com.example.elisaappbe.model.EnglishGrammarTheory;
import com.example.elisaappbe.model.EnglishMultipleChoiceQuestion;
import com.example.elisaappbe.model.EnglishSentenceRewritingQuestion;
import com.example.elisaappbe.repository.EnglishExerciseRepository;
import com.example.elisaappbe.repository.EnglishGrammarTheoryRepository;
import com.example.elisaappbe.repository.EnglishMultipleChoiceQuestionRepository;
import com.example.elisaappbe.repository.EnglishSentenceRewritingQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnglishExerciseServiceImpl implements EnglishExerciseService {

    @Autowired
    private EnglishExerciseRepository exerciseRepository;

    @Autowired
    private EnglishMultipleChoiceQuestionRepository englishMultipleRepository;

    @Autowired
    private EnglishSentenceRewritingQuestionRepository englishSentenceRewritingRepository;


    private EnglishMultipleChoiceResponse toResponseMultiple(EnglishMultipleChoiceQuestion vocab) {
        EnglishMultipleChoiceResponse resp = new EnglishMultipleChoiceResponse();
        resp.setQuestionId(vocab.getQuestionId());
        resp.setQuestionText(vocab.getQuestionText());
        resp.setOptionA(vocab.getOptionA());
        resp.setOptionB(vocab.getOptionB());
        resp.setOptionC(vocab.getOptionC());
        resp.setOptionD(vocab.getOptionD());
        resp.setCorrectAnswer(vocab.getCorrectAnswer());
        resp.setLinkMedia(vocab.getLinkMedia());
        return resp;
    }

    private EnglishSentenceRewritingResponse toResponseSentenceRewriting(EnglishSentenceRewritingQuestion vocab) {
        EnglishSentenceRewritingResponse resp = new EnglishSentenceRewritingResponse();
        resp.setQuestionId(vocab.getQuestionID());
        resp.setOriginalSentence(vocab.getOriginalSentence());
        resp.setRewrittenSentence(vocab.getRewrittenSentence());
        resp.setHint(vocab.getHint());
        resp.setLinkMedia(vocab.getLinkMedia());
        return resp;
    }


    @Override
    public EnglishExerciseResponse getExerciseByLesson(long id) {
        EnglishExerciseResponse englishExerciseResponse = new EnglishExerciseResponse();
        List<EnglishExercise> listResult = exerciseRepository.findByEnglishLesson_LessonId(id);
        for (EnglishExercise exercise : listResult) {
            String type = exercise.getExerciseType();
            if ("mc".equals(type)) {
                List<EnglishMultipleChoiceQuestion> multipleResult=  exercise.getMultipleChoiceQuestions();
                englishExerciseResponse.setListMultipleChoice(
                        multipleResult.stream()
                                .map(this::toResponseMultiple)
                                    .collect(Collectors.toList())

                );
            }else if("sr".equals(type)){
                List<EnglishSentenceRewritingQuestion> sentenceRewritingResult=  exercise.getRewritingQuestions();
                englishExerciseResponse.setListSentenceRewriting(
                        sentenceRewritingResult.stream()
                                .map(this::toResponseSentenceRewriting)
                                .collect(Collectors.toList())

                );
            }
        }

        return englishExerciseResponse;
    }
}
