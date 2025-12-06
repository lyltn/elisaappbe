package com.example.elisaappbe.dto.req;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EnglishGrammarRequest {
    private String grammarTitle;
    private String grammarContent;
    private String grammarUsage;
    private String grammarExample;
}
