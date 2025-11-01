package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class ExerciseResponse {
    private Long exerciseId;
    private Long lessonId;
    private String exerciseTitle;
    private String exerciseType;
    private String exerciseDescription;
}