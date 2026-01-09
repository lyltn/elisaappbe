package com.example.elisaappbe.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishVocabularyRequest {
    private String word;
    private String meaning;
    private String example;
    private String type;
    private String ipa;
    private MultipartFile file;
}
