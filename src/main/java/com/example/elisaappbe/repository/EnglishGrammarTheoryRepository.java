package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishGrammarTheory;
import com.example.elisaappbe.model.EnglishVocabularyTheory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishGrammarTheoryRepository extends JpaRepository<EnglishGrammarTheory, Long> {
    List<EnglishGrammarTheory> findByEnglishLesson_LessonId(Long lessonId);
}
