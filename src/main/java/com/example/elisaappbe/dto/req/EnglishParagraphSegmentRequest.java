package com.example.elisaappbe.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishParagraphSegmentRequest {
    private String content;
    private Integer correctOrder;
}
