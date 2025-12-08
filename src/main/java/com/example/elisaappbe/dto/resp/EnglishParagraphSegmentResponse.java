package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishParagraphSegmentResponse {
    private Long id;
    private String content;
    private Integer correctOrder;
}