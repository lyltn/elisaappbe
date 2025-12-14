package com.example.elisaappbe.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishOrderingExerciseRequest {
    private String title;
    private String hint;
    private List<EnglishParagraphSegmentRequest> paragraphs;
}