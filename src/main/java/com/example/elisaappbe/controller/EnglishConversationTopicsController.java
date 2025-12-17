package com.example.elisaappbe.controller;

import com.example.elisaappbe.dto.resp.EnglishConversationTopicsResponse;
import com.example.elisaappbe.service.englishConversationTopics.EnglishConversationTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conversation-topics")
public class EnglishConversationTopicsController {
    @Autowired
    private EnglishConversationTopicsService conversationTopicsService;

    @GetMapping("/list-topics")
    public List<EnglishConversationTopicsResponse> getListTopics(){
        return conversationTopicsService.getListTopics();
    }

    @GetMapping("/{topicId}")
    public EnglishConversationTopicsResponse getTopicById(@PathVariable Long topicId){
        return conversationTopicsService.getTopicsById(topicId);
    }
}
