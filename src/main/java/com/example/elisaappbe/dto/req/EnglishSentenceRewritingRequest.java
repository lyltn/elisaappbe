package com.example.elisaappbe.dto.req;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnglishSentenceRewritingRequest {
    private String originalSentence;
    private String rewrittenSentence;
    private String hint;
}
