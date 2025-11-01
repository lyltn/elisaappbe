package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class SentenceRewritingQuestionResponse {
    private Long questionId;
    private Long exerciseId;
    private String originalSentence;
    private String rewrittenSentence;
    private String linkMedia;
}
