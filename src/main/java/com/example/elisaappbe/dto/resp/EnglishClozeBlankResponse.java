package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishClozeBlankResponse {
    private Long id;
    private Integer blankIndex;
    private String correctAnswer;
    private String hint;
}