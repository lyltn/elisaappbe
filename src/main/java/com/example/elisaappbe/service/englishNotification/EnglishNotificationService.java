package com.example.elisaappbe.service.englishNotification;

import com.example.elisaappbe.dto.req.EnglishNotificationRequest;
import com.example.elisaappbe.dto.resp.EnglishNotificationResponse;
import com.example.elisaappbe.dto.resp.EnglishUserProgressResponse;

import java.util.List;

public interface EnglishNotificationService {
    List<EnglishNotificationResponse> getNotificationByUserId(long userId);
    EnglishNotificationResponse createNotification(EnglishNotificationRequest res);
    List<EnglishNotificationResponse> updateIsRead(long userId);
}
