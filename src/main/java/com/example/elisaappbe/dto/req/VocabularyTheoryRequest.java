package com.example.elisaappbe.dto.req;

import lombok.Data;
import com.example.elisaappbe.model.Lesson;

@Data
public class VocabularyTheoryRequest {
    private Long lessonId;
    private String word;
    private String meaning;
    private String example;
    private String image;
}
