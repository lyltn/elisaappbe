package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishOrderingExerciseResponse {
    private Long id;
    private String title;
    private String hint;
    private List<EnglishParagraphSegmentResponse> paragraphs;
}