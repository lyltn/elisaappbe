package com.example.elisaappbe.dto.resp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishChatbotResponse {
    private String userText;
    private String answerChatbot;
    private String answerVietnamese;
}
