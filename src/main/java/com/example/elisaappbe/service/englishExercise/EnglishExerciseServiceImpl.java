package com.example.elisaappbe.service.englishExercise;

import com.example.elisaappbe.dto.req.*;
import com.example.elisaappbe.dto.resp.*;
import com.example.elisaappbe.model.*;
import com.example.elisaappbe.repository.*;
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

    @Autowired
    private EnglishListeningDictationRepository listeningDictationRepository;

    @Autowired
    private EnglishClozeBlankRepository clozeBlankRepository;

    @Autowired
    private EnglishClozeExerciseRepository clozeExerciseRepository;

    @Autowired
    private EnglishParagraphSegmentRepository paragraphSegmentRepository;

    @Autowired
    private EnglishOrderingExerciseRepository orderingExerciseRepository;


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

    private EnglishListeningDictationResponse toResponseListeningDictation(EnglishListeningDictation vocab){
        EnglishListeningDictationResponse resp = new EnglishListeningDictationResponse();
        resp.setId(vocab.getId());
        resp.setTitle(vocab.getTitle());
        resp.setAudioUrl(vocab.getAudioUrl());
        resp.setTranscript(vocab.getTranscript());
        resp.setHintText(vocab.getHintText());
        return resp;
    }

    private EnglishClozeBlankResponse toResponseClozeBlank(EnglishClozeBlank vocab){
        EnglishClozeBlankResponse resp = new EnglishClozeBlankResponse();
        resp.setId(vocab.getId());
        resp.setBlankIndex(vocab.getBlankIndex());
        resp.setCorrectAnswer(vocab.getCorrectAnswer());
        resp.setHint(vocab.getHint());
        return resp;
    }

    private EnglishClozeExerciseResponse toResponseClozeExercise(EnglishClozeExercise vocab){
        EnglishClozeExerciseResponse resp = new EnglishClozeExerciseResponse();
        resp.setId(vocab.getId());
        resp.setTitle(vocab.getTitle());
        resp.setContent(vocab.getContent());
        resp.setBlanks(vocab.getBlanks().stream().map(this::toResponseClozeBlank).collect(Collectors.toList()));
        return resp;
    }

    private EnglishParagraphSegmentResponse toResponseParagraphSegment(EnglishParagraphSegment vocab){
        EnglishParagraphSegmentResponse resp = new EnglishParagraphSegmentResponse();
        resp.setId(vocab.getId());
        resp.setContent(vocab.getContent());
        resp.setCorrectOrder(vocab.getCorrectOrder());
        return resp;
    }

    private EnglishOrderingExerciseResponse toResponseOrderingExercise(EnglishOrderingExercise vocab){
        EnglishOrderingExerciseResponse resp = new EnglishOrderingExerciseResponse();
        resp.setId(vocab.getId());
        resp.setTitle(vocab.getTitle());
        resp.setHint(vocab.getHint());
        resp.setParagraphs(vocab.getParagraphs().stream().map(this::toResponseParagraphSegment).collect(Collectors.toList()));
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

    // GET QUESTION FOR CHALLENGE

    @Override
    public List<EnglishMultipleChoiceResponse> getMultipleChoiceForChallenge(Long lessonId) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"mc");
        List<EnglishMultipleChoiceQuestion> listQuestion = getEnglishExercise.getMultipleChoiceQuestions();
        return  listQuestion.stream()
                .map(this::toResponseMultiple)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnglishSentenceRewritingResponse> getSentenceRewritingForChallenge(Long lessonId) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"sr");
        List<EnglishSentenceRewritingQuestion> listQuestion = getEnglishExercise.getRewritingQuestions();
        return listQuestion.stream()
                .map(this::toResponseSentenceRewriting)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnglishListeningDictationResponse> getListeningDictationForChallenge(Long lessonId) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"ld");
        List<EnglishListeningDictation> listQuestion = getEnglishExercise.getListeningDictation();
        return listQuestion.stream()
                .map(this::toResponseListeningDictation)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnglishClozeExerciseResponse> getClozeForChallenge(Long lessonId) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"cb");
        List<EnglishClozeExercise> listQuestion = getEnglishExercise.getClozeExercise();
        return listQuestion.stream()
                .map(this::toResponseClozeExercise)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnglishOrderingExerciseResponse> getOrderingForChallenge(Long lessonId) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"pa");
        List<EnglishOrderingExercise> listQuestion = getEnglishExercise.getOrderingExercise();
        return listQuestion.stream()
                .map(this::toResponseOrderingExercise)
                .collect(Collectors.toList());
    }

    // ===========

    @Override
    public EnglishListeningDictationResponse createListeningDictation(Long lessonId, EnglishListeningDictationRequest req) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"ld");
        EnglishListeningDictation newQuestion = new EnglishListeningDictation();
        newQuestion.setTitle(req.getTitle());
        newQuestion.setAudioUrl(req.getAudioUrl());
        newQuestion.setTranscript(req.getTranscript());
        newQuestion.setHintText(req.getHintText());
        newQuestion.setEnglishExercise(getEnglishExercise);

        newQuestion = listeningDictationRepository.save(newQuestion);

        return toResponseListeningDictation(newQuestion);
    }

    @Override
    public EnglishListeningDictationResponse updateListeningDictation(Long questionId, EnglishListeningDictationRequest req) {
        Optional<EnglishListeningDictation> getQuestion = listeningDictationRepository.findById(questionId);
        EnglishListeningDictation updateQuestion = new EnglishListeningDictation();
        if(getQuestion.isPresent()){
            updateQuestion = getQuestion.get();
            updateQuestion.setTitle(req.getTitle());
            updateQuestion.setAudioUrl(req.getAudioUrl());
            updateQuestion.setTranscript(req.getTranscript());
            updateQuestion.setHintText(req.getHintText());
            updateQuestion = listeningDictationRepository.save(updateQuestion);
        }
        return toResponseListeningDictation(updateQuestion);
    }

    @Override
    public void deleteListeningDictation(Long questionId) {
        Optional<EnglishListeningDictation> getQuestion = listeningDictationRepository.findById(questionId);
        if(getQuestion.isPresent()){
            listeningDictationRepository.deleteById(questionId);
        }
    }

    // ================ =============
    @Override
    public EnglishClozeExerciseResponse createClozeExercise(Long lessonId, EnglishClozeExerciseRequest req) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"cb");
        EnglishClozeExercise newQuestion = new EnglishClozeExercise();
        newQuestion.setTitle(req.getTitle());
        newQuestion.setContent(req.getContent());
        newQuestion.setEnglishExercise(getEnglishExercise);

        if(req.getBlanks() != null){
            List<EnglishClozeBlank> blankEntities = req.getBlanks().stream().map(blankReq -> {
                EnglishClozeBlank blank = new EnglishClozeBlank();
                blank.setBlankIndex(blankReq.getBlankIndex());
                blank.setCorrectAnswer(blankReq.getCorrectAnswer());
                blank.setHint(blankReq.getHint());
                blank.setExercise(newQuestion);
                return blank;
            }).collect(Collectors.toList());

            newQuestion.setBlanks(blankEntities);
        }

        EnglishClozeExercise savedQuestion = clozeExerciseRepository.save(newQuestion);

        return toResponseClozeExercise(savedQuestion);
    }

    @Override
    public EnglishClozeExerciseResponse updateClozeExercise(Long questionId, EnglishClozeExerciseRequest req) {
        Optional<EnglishClozeExercise> getQuestion = clozeExerciseRepository.findById(questionId);
        if(getQuestion.isPresent()){
            EnglishClozeExercise updateQuestion = getQuestion.get();
            updateQuestion.setTitle(req.getTitle());
            updateQuestion.setContent(req.getContent());

            if(req.getBlanks() != null){
                List<EnglishClozeBlank> blankEntities = req.getBlanks().stream().map(blankReq -> {
                    EnglishClozeBlank blank = new EnglishClozeBlank();
                    blank.setBlankIndex(blankReq.getBlankIndex());
                    blank.setCorrectAnswer(blankReq.getCorrectAnswer());
                    blank.setHint(blankReq.getHint());
                    blank.setExercise(updateQuestion);
                    return blank;
                }).collect(Collectors.toList());

                updateQuestion.setBlanks(blankEntities);
            }
            EnglishClozeExercise savedQuestion = clozeExerciseRepository.save(updateQuestion);
            return toResponseClozeExercise(savedQuestion);
        }
        return null;
    }

    @Override
    public void deleteClozeExercise(Long questionId) {
        Optional<EnglishClozeExercise> getQuestion = clozeExerciseRepository.findById(questionId);
        if(getQuestion.isPresent()){
            clozeExerciseRepository.deleteById(questionId);
        }
    }

    // ================ =============
    @Override
    public EnglishOrderingExerciseResponse createOrderingExercise(Long lessonId, EnglishOrderingExerciseRequest req) {
        EnglishExercise getEnglishExercise = exerciseRepository.findByLessonIdAndType(lessonId,"pa");
        EnglishOrderingExercise newQuestion = new EnglishOrderingExercise();
        newQuestion.setTitle(req.getTitle());
        newQuestion.setHint(req.getHint());
        newQuestion.setEnglishExercise(getEnglishExercise);

        if(req.getParagraphs() != null){
            List<EnglishParagraphSegment> paragraphSegmentEntities = req.getParagraphs().stream().map(paragraphReq ->{
                EnglishParagraphSegment paragraph = new EnglishParagraphSegment();
                paragraph.setContent(paragraphReq.getContent());
                paragraph.setCorrectOrder(paragraphReq.getCorrectOrder());
                paragraph.setExercise(newQuestion);
                return paragraph;
            }).collect(Collectors.toList());

            newQuestion.setParagraphs(paragraphSegmentEntities);

        }
        EnglishOrderingExercise saveOrderingExercise = orderingExerciseRepository.save(newQuestion);

        return toResponseOrderingExercise(saveOrderingExercise);
    }

    @Override
    public EnglishOrderingExerciseResponse updateOrderingExercise(Long questionId, EnglishOrderingExerciseRequest req) {
        Optional<EnglishOrderingExercise> getQuestion = orderingExerciseRepository.findById(questionId);
        if(getQuestion.isPresent()){
            EnglishOrderingExercise updateQuestion = getQuestion.get();
            updateQuestion.setTitle(req.getTitle());
            updateQuestion.setHint(req.getHint());

            if(req.getParagraphs() != null){
                List<EnglishParagraphSegment> paragraphSegmentEntities = req.getParagraphs().stream().map(paragraphReq ->{
                    EnglishParagraphSegment paragraph = new EnglishParagraphSegment();
                    paragraph.setContent(paragraphReq.getContent());
                    paragraph.setCorrectOrder(paragraphReq.getCorrectOrder());
                    paragraph.setExercise(updateQuestion);
                    return paragraph;
                }).collect(Collectors.toList());

                updateQuestion.setParagraphs(paragraphSegmentEntities);

                EnglishOrderingExercise saveOrderingExercise = orderingExerciseRepository.save(updateQuestion);

                return toResponseOrderingExercise(saveOrderingExercise);
            }

        }
        return null;
    }

    @Override
    public void deleteOrderingExercise(Long questionId) {
        Optional<EnglishOrderingExercise> getQuestion = orderingExerciseRepository.findById(questionId);
        if(getQuestion.isPresent()){
            orderingExerciseRepository.deleteById(questionId);
        }
    }


}
