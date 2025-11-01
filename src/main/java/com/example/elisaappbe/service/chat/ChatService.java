package com.example.elisaappbe.service.chat;

import com.example.elisaappbe.dto.req.CreateChatConversationRequest;
import com.example.elisaappbe.dto.req.SendChatMessageRequest;
import com.example.elisaappbe.dto.resp.ChatConversationResponse;
import com.example.elisaappbe.dto.resp.ChatMessageResponse;
import com.example.elisaappbe.dto.resp.ChatResponsePair;

import java.util.List;

public interface ChatService {
    ChatConversationResponse createConversation(CreateChatConversationRequest request);
    ChatResponsePair sendMessage(Long conversationId, SendChatMessageRequest request);
    List<ChatMessageResponse> getConversationMessages(Long conversationId);
    List<ChatConversationResponse> getUserConversations(Long userId);
    void deleteConversation(Long conversationId) ;
}