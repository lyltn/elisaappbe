package com.example.elisaappbe.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishListeningDictationRequest {
    private String title;
    private String transcript;
    private String hintText;
    private String audioUrl;
}
