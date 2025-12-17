package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishConversationTopics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnglishConversationTopicsRepository extends JpaRepository<EnglishConversationTopics, Long> {
    @Query("SELECT t FROM EnglishConversationTopics t WHERE t.status = 'active'")
    List<EnglishConversationTopics> findAllActiveTopics();
}
