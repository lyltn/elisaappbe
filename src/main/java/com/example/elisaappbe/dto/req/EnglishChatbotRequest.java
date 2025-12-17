package com.example.elisaappbe.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnglishChatbotRequest {
    private String message;
    private String topic;
    private String level;
}
