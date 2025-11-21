package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishExerciseRepository extends JpaRepository<EnglishExercise, Long> {
    List<EnglishExercise> findByEnglishLesson_LessonId(Long lessonId);
}
