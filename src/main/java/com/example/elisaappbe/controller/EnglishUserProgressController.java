package com.example.elisaappbe.controller;
import com.example.elisaappbe.dto.req.EnglishUserProgressRequest;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;
import com.example.elisaappbe.service.englishUserProgress.EnglishUserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/english-user-progress")
public class EnglishUserProgressController {

    @Autowired
    private EnglishUserProgressService userProgressService;

    @GetMapping("/{id}")
    public EnglishUserProgressResponse getUserProgressByUserId(@PathVariable Long id) {
        return userProgressService.getUserProgress(id);
    }

    // --- HÀM MỚI THÊM VÀO ---
    @PostMapping("/update")
    public ResponseEntity<EnglishUserProgressResponse> updateUserProgress(@RequestBody EnglishUserProgressRequest request) {
        EnglishUserProgressResponse response = userProgressService.updateProgress(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<EnglishUserProgressResponse> createUserProgress(@PathVariable Long id) {
        EnglishUserProgressResponse response = userProgressService.createUserProgress(id);
        return ResponseEntity.ok(response);
    }

}
