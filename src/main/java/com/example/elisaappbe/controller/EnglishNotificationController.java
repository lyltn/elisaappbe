package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.req.EnglishNotificationRequest;
import com.example.elisaappbe.dto.req.EnglishUserProgressRequest;
import com.example.elisaappbe.dto.resp.EnglishNotificationResponse;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;
import com.example.elisaappbe.service.englishNotification.EnglishNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/english-notification")
public class EnglishNotificationController {
    @Autowired
    private EnglishNotificationService notificationService;

    @GetMapping("/{id}")
    public List<EnglishNotificationResponse> getNotificationByUserId(@PathVariable Long id) {
        return notificationService.getNotificationByUserId(id);
    }

    @PostMapping("/create")
    public EnglishNotificationResponse createNotification (@RequestBody EnglishNotificationRequest request) {
        return notificationService.createNotification(request);
    }

    @PutMapping("/update/{id}")
    public List<EnglishNotificationResponse> updateNotification (@PathVariable Long id) {
        return notificationService.updateIsRead(id);
    }
}
