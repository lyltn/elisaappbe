package com.example.elisaappbe.dto.resp;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnglishSentenceRewritingResponse {
    private long questionId;
    private String originalSentence;
    private String rewrittenSentence;
    private String hint;
    private String linkMedia;
}
