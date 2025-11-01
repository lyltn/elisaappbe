package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class MultipleChoiceQuestionRequest {
    private Long exerciseId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String linkMedia;
}