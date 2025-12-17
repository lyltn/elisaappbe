package com.example.elisaappbe.service.englishConversationTopics;

import com.example.elisaappbe.dto.resp.EnglishConversationTopicsResponse;

import java.util.List;

public interface EnglishConversationTopicsService {
    List<EnglishConversationTopicsResponse> getListTopics();
    EnglishConversationTopicsResponse getTopicsById(Long topicId);
}
