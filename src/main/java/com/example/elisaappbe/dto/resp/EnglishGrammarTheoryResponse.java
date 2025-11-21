package com.example.elisaappbe.dto.resp;

import lombok.Data;

@Data
public class EnglishGrammarTheoryResponse {
    private Long grammarId;
    private String grammarTitle;
    private String grammarContent;
    private String grammarUsage;
    private String grammarExample;
}
