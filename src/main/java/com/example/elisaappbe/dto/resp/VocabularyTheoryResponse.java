package com.example.elisaappbe.dto.resp;

import lombok.Data;
import com.example.elisaappbe.model.Lesson;

@Data
public class VocabularyTheoryResponse {
    private long vocabId;
    private long lessonId;
    private String word;
    private String meaning;
    private String example;
    private Long levelId;
    private String levelName;
    private String image;
}
