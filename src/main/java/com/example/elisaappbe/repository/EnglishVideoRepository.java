package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishGrammarTheory;
import com.example.elisaappbe.model.EnglishVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishVideoRepository extends JpaRepository<EnglishVideo, Long> {
    List<EnglishVideo> findByLevel(String level);
}
