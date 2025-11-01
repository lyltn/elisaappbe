package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.MultipleChoiceQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {
    List<MultipleChoiceQuestion> findByExercise_ExerciseId(Long exerciseId);

    //admin
    Page<MultipleChoiceQuestion> findByExercise_Lesson_LessonIdAndQuestionTextContainingIgnoreCase(Long lessonId, String keyword, Pageable pageable);}
