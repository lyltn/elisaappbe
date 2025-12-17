package com.example.elisaappbe.dto.resp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishConversationTopicsResponse {
    private Long topicId;
    private String titleTopics;
    private String titleEnglish;
    private String description;
    private String image;
}
