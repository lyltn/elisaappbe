package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.*;
import com.example.elisaappbe.dto.resp.*;
import com.example.elisaappbe.service.cloud.CloudinaryService;
import com.example.elisaappbe.service.englishExercise.EnglishExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/english-exercise")
public class EnglishExerciseController {

    @Autowired
    private EnglishExerciseService exerciseService;

    @Autowired
    private CloudinaryService cloudService;

    @GetMapping("/{id}")
    public EnglishExerciseResponse getVocabularyTheoriesByLesson(@PathVariable Long id) {
        return exerciseService.getExerciseByLesson(id);
    }

    @PostMapping("/create/multiple/{lessonId}")
    public EnglishMultipleChoiceResponse createMultipleQuestion(@PathVariable Long lessonId, @RequestBody EnglishMultipleChoiceRequest req){
        return exerciseService.createMultipleChoiceQuestion(lessonId,req);
    }

    @PutMapping("/update/multiple/{questionId}")
    public EnglishMultipleChoiceResponse updateMultipleQuestion(@PathVariable Long questionId, @RequestBody EnglishMultipleChoiceRequest req){
        return exerciseService.updateMultipleChoiceQuestion(questionId,req);
    }

    @DeleteMapping("/delete/multiple/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteMultipleQuestion(@PathVariable Long questionId){
        exerciseService.deleteMultipleChoiceQuestion(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa câu trắc nghiệm thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/sentence-rewriting/{lessonId}")
    public EnglishSentenceRewritingResponse createSentenceRewritingQuestion(@PathVariable Long lessonId, @RequestBody EnglishSentenceRewritingRequest req){
        return exerciseService.createSentenceRewritingQuestion(lessonId,req);
    }

    @PutMapping("/update/sentence-rewriting/{questionId}")
    public EnglishSentenceRewritingResponse updateSentenceRewritingQuestion(@PathVariable Long questionId, @RequestBody EnglishSentenceRewritingRequest req){
        return exerciseService.updateSentenceRewritingQuestion(questionId,req);
    }

    @DeleteMapping("/delete/sentence-rewriting/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteSentenceRewritingQuestion(@PathVariable Long questionId){
        exerciseService.deleteSentenceRewritingQuestion(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa ghép câu thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }

    // === FOR CHALLENGE ===
    @GetMapping("/challenge/multiple/{lessonId}")
    public List<EnglishMultipleChoiceResponse> getMultipleChoiceForChallenge(@PathVariable Long lessonId){
        return exerciseService.getMultipleChoiceForChallenge(lessonId);
    }

    @GetMapping("/challenge/sentence-rewriting/{lessonId}")
    public List<EnglishSentenceRewritingResponse> getSentenceRewritingForChallenge(@PathVariable Long lessonId){
        return exerciseService.getSentenceRewritingForChallenge(lessonId);
    }

    @GetMapping("/challenge/listening-dictation/{lessonId}")
    public List<EnglishListeningDictationResponse> getListeningDictationForChallenge(@PathVariable Long lessonId){
        return exerciseService.getListeningDictationForChallenge(lessonId);
    }

    @GetMapping("/challenge/cloze/{lessonId}")
    public List<EnglishClozeExerciseResponse> getClozeForChallenge(@PathVariable Long lessonId){
        return exerciseService.getClozeForChallenge(lessonId);
    }

    @GetMapping("/challenge/ordering/{lessonId}")
    public List<EnglishOrderingExerciseResponse> getOrderingForChallenge(@PathVariable Long lessonId){
        return exerciseService.getOrderingForChallenge(lessonId);
    }

    @PostMapping(value = "/create/listening-dictation/{lessonId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EnglishListeningDictationResponse> createListeningDictation(@PathVariable Long lessonId,
                                                                                      @RequestPart("data") EnglishListeningDictationRequest req,
                                                                                      @RequestParam("file") MultipartFile file ){
        if (file.isEmpty()) {
            throw new RuntimeException("File không được để trống");
        }
        try {
            Random rand = new Random();
            int rd= rand.nextInt(1000000) + 1;
            String publicId = "ListeningDictation_" + lessonId + "_"+rd;
            String avatarUrl = cloudService.uploadFile(file,publicId);
            req.setAudioUrl(avatarUrl);
            EnglishListeningDictationResponse create = exerciseService.createListeningDictation(lessonId, req);
            return ResponseEntity.ok(create);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/listening-dictation/{questionId}")
    public EnglishListeningDictationResponse updateListeningDictation(@PathVariable Long questionId,
                                                                      @RequestBody EnglishListeningDictationRequest req
                                                                      ){
        return exerciseService.updateListeningDictation(questionId, req);
    }

    @DeleteMapping("/delete/listening-dictation/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteListeningDictation(@PathVariable Long questionId){
        exerciseService.deleteListeningDictation(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa phần câu hỏi nghe chép lại thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/create/cloze-exercise/{lessonId}")
    public EnglishClozeExerciseResponse createClozeExercise(@PathVariable Long lessonId, @RequestBody EnglishClozeExerciseRequest req){
        return exerciseService.createClozeExercise(lessonId, req);
    }

    @PutMapping("/update/cloze-exercise/{questionId}")
    public EnglishClozeExerciseResponse updateClozeExercise(@PathVariable Long questionId,
                                                            @RequestBody EnglishClozeExerciseRequest req){
        return exerciseService.updateClozeExercise(questionId, req);
    }

    @DeleteMapping("/delete/cloze-exercise/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteClozeExercise(@PathVariable Long questionId){
        exerciseService.deleteClozeExercise(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa phần câu hỏi điền vào chỗ trống thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/create/ordering-exercise/{lessonId}")
    public EnglishOrderingExerciseResponse createOrderingExercise(@PathVariable Long lessonId, @RequestBody EnglishOrderingExerciseRequest req){
        return exerciseService.createOrderingExercise(lessonId, req);
    }

    @PutMapping("/update/ordering-exercise/{questionId}")
    public EnglishOrderingExerciseResponse updateOrderingExercise(@PathVariable Long questionId,
                                                            @RequestBody EnglishOrderingExerciseRequest req){
        return exerciseService.updateOrderingExercise(questionId, req);
    }

    @DeleteMapping("/delete/ordering-exercise/{questionId}")
    public ResponseEntity<Map<String, Object>> deleteOrderingExercise(@PathVariable Long questionId){
        exerciseService.deleteOrderingExercise(questionId);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Xóa phần câu hỏi sắp xếp câu thành công");
        response.put("deletedId", questionId);
        return ResponseEntity.ok(response);
    }

}
