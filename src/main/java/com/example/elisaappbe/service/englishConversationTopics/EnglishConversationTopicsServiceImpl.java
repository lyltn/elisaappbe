package com.example.elisaappbe.service.englishConversationTopics;

import com.example.elisaappbe.dto.resp.EnglishConversationTopicsResponse;
import com.example.elisaappbe.model.EnglishConversationTopics;
import com.example.elisaappbe.repository.EnglishConversationTopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnglishConversationTopicsServiceImpl implements EnglishConversationTopicsService{

    @Autowired
    private EnglishConversationTopicsRepository conversationTopicsRepositoru;

    private EnglishConversationTopicsResponse toResponse(EnglishConversationTopics vocab){
        EnglishConversationTopicsResponse resp = new EnglishConversationTopicsResponse();
        resp.setTopicId(vocab.getTopicId());
        resp.setTitleTopics(vocab.getTitleTopics());
        resp.setTitleEnglish(vocab.getTitleEnglish());
        resp.setDescription(vocab.getDescription());
        resp.setImage(vocab.getImage());
         return resp;
    }

    @Override
    public List<EnglishConversationTopicsResponse> getListTopics() {
        return conversationTopicsRepositoru.findAllActiveTopics().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public EnglishConversationTopicsResponse getTopicsById(Long topicId) {
        Optional<EnglishConversationTopics> getTopic = conversationTopicsRepositoru.findById(topicId);
        if(getTopic.isPresent()){
            return toResponse(getTopic.get());
        }
        return null;
    }
}
