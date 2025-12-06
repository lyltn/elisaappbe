package com.example.elisaappbe.dto.req;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnglishMultipleChoiceRequest {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
}
