package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class EnglishVocabularyTheoryResponse {
    private long vocabId;
    private String word;
    private String meaning;
    private String type;
    private String ipa;
    private String example;
    private String image;
}
