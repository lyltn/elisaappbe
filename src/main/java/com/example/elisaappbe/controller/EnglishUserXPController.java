package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishUserProgressRequest;
import com.example.elisaappbe.dto.req.EnglishUserXPRequest;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;
import com.example.elisaappbe.dto.resp.EnglishUserXPResponse;
import com.example.elisaappbe.service.englishUserXP.EnglishUserXPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/english-user-xp")
public class EnglishUserXPController {
    @Autowired
    private EnglishUserXPService userXPService;

    @GetMapping("/{id}")
    public EnglishUserXPResponse getUserXPByUserId(@PathVariable Long id) {
        return userXPService.getUserXP(id);
    }

    @PostMapping("/update")
    public ResponseEntity<EnglishUserXPResponse> updateUserXP(@RequestBody EnglishUserXPRequest request) {
        EnglishUserXPResponse response = userXPService.updateUserXP(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<EnglishUserXPResponse> createUserProgress(@PathVariable Long id) {
        EnglishUserXPResponse response = userXPService.createUserXP(id);
        return ResponseEntity.ok(response);
    }
}
