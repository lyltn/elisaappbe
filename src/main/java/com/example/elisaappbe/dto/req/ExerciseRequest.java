package com.example.elisaappbe.dto.req;

import lombok.Data;

@Data
public class ExerciseRequest {
    private Long lessonId;
    private String exerciseTitle;
    private String exerciseType;
    private String exerciseDescription;
}
