package com.example.elisaappbe.dto.resp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishListeningDictationResponse {
    private Long id;
    private String title;
    private String audioUrl;
    private String transcript;
    private String hintText;
}
