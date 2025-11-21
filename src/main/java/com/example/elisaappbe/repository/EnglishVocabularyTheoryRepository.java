package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishVocabularyTheory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishVocabularyTheoryRepository extends JpaRepository<EnglishVocabularyTheory, Long> {
    List<EnglishVocabularyTheory> findByEnglishLesson_LessonId(Long lessonId);
    Page<EnglishVocabularyTheory> findByEnglishLesson_LessonId(Long lessonId, Pageable pageable);
}
