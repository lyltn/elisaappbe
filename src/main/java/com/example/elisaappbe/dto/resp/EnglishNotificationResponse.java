package com.example.elisaappbe.dto.resp;

import com.example.elisaappbe.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishNotificationResponse {
    private Long notificationId;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime sendTime;
    private String type;
    private Boolean isRead;

}
