package com.example.elisaappbe.service.englishExercise;

import com.example.elisaappbe.dto.req.EnglishMultipleChoiceRequest;
import com.example.elisaappbe.dto.req.EnglishSentenceRewritingRequest;
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
import java.util.Optional;
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

    @Override
    public EnglishMultipleChoiceResponse createMultipleChoiceQuestion(Long lessonId, EnglishMultipleChoiceRequest req) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"mc");
        EnglishMultipleChoiceQuestion newQuestion = new EnglishMultipleChoiceQuestion();
        newQuestion.setEnglishExercise(getEnglishExercise);
        newQuestion.setQuestionText(req.getQuestionText());
        newQuestion.setOptionA(req.getOptionA());
        newQuestion.setOptionB(req.getOptionB());
        newQuestion.setOptionC(req.getOptionC());
        newQuestion.setOptionD(req.getOptionD());
        newQuestion.setCorrectAnswer(req.getCorrectAnswer());
        newQuestion = englishMultipleRepository.save(newQuestion);

        return toResponseMultiple(newQuestion);
    }

    @Override
    public EnglishMultipleChoiceResponse updateMultipleChoiceQuestion(Long questionId, EnglishMultipleChoiceRequest req) {
        Optional<EnglishMultipleChoiceQuestion> getQuestion = englishMultipleRepository.findById(questionId);
        EnglishMultipleChoiceQuestion updateQuestion = new EnglishMultipleChoiceQuestion();
        if(getQuestion.isPresent()){
            updateQuestion = getQuestion.get();
            updateQuestion.setQuestionText(req.getQuestionText());
            updateQuestion.setOptionA(req.getOptionA());
            updateQuestion.setOptionB(req.getOptionB());
            updateQuestion.setOptionC(req.getOptionC());
            updateQuestion.setOptionD(req.getOptionD());
            updateQuestion.setCorrectAnswer(req.getCorrectAnswer());
            updateQuestion = englishMultipleRepository.save(updateQuestion);
        }
        return toResponseMultiple(updateQuestion);
    }

    @Override
    public void deleteMultipleChoiceQuestion(Long questionId) {
        Optional<EnglishMultipleChoiceQuestion> getQuestion = englishMultipleRepository.findById(questionId);
        if(getQuestion.isPresent()){
            englishMultipleRepository.deleteById(questionId);
        }
    }

    @Override
    public EnglishSentenceRewritingResponse createSentenceRewritingQuestion(Long lessonId, EnglishSentenceRewritingRequest req) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"sr");
        EnglishSentenceRewritingQuestion newQuestion = new EnglishSentenceRewritingQuestion();
        newQuestion.setEnglishExercise(getEnglishExercise);
        newQuestion.setOriginalSentence(req.getOriginalSentence());
        newQuestion.setRewrittenSentence(req.getRewrittenSentence());
        newQuestion.setHint(req.getHint());

        newQuestion= englishSentenceRewritingRepository.save(newQuestion);
        return toResponseSentenceRewriting(newQuestion);
    }

    @Override
    public EnglishSentenceRewritingResponse updateSentenceRewritingQuestion(Long questionId, EnglishSentenceRewritingRequest req) {
        Optional<EnglishSentenceRewritingQuestion> getQuestion = englishSentenceRewritingRepository.findById(questionId);
        EnglishSentenceRewritingQuestion updateQuestion = new EnglishSentenceRewritingQuestion();
        if(getQuestion.isPresent()){
            updateQuestion = getQuestion.get();
            updateQuestion.setOriginalSentence(req.getOriginalSentence());
            updateQuestion.setRewrittenSentence(req.getRewrittenSentence());
            updateQuestion.setHint(req.getHint());

            updateQuestion = englishSentenceRewritingRepository.save(updateQuestion);
        }
        return toResponseSentenceRewriting(updateQuestion);
    }

    @Override
    public void deleteSentenceRewritingQuestion(Long questionId) {
        Optional<EnglishSentenceRewritingQuestion> getQuestion = englishSentenceRewritingRepository.findById(questionId);
        if(getQuestion.isPresent()){
            englishSentenceRewritingRepository.deleteById(questionId);
        }

    }
}
