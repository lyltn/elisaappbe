package com.example.elisaappbe.dto.req;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnglishClozeBlankRequest {
    private Integer blankIndex;
    private String correctAnswer;
    private String hint;
}
