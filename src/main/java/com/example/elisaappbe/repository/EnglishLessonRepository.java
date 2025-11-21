package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnglishLessonRepository extends JpaRepository<EnglishLesson, Long> {
    EnglishLesson findByLessonId(long lessonId);
}
