package com.example.elisaappbe.dto.resp;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnglishMultipleChoiceResponse {
    private Long questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String linkMedia;
}
