package com.example.elisaappbe.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishCheckMessageResponse {
    private String originalSentence;
    private Float score;
    private String editedSentence;
    private String hintText;
}
