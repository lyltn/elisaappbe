package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class EnglishNotificationRequest {
    private Long userId;
    private String title;
    private String content;
    private String imageUrl;
    private String type;
}
