package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnglishExerciseRepository extends JpaRepository<EnglishExercise, Long> {
    List<EnglishExercise> findByEnglishLesson_LessonId(Long lessonId);

    @Query("SELECT e FROM EnglishExercise e WHERE e.englishLesson.id = :lessonId AND e.exerciseType = :exerciseType")
    EnglishExercise findByLessonIdAndType(@Param("lessonId") Long lessonId,
                                                @Param("exerciseType") String exerciseType);
}
