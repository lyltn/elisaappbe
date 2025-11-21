package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishMultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishMultipleChoiceQuestionRepository extends JpaRepository<EnglishMultipleChoiceQuestion, Long> {
    List<EnglishMultipleChoiceQuestion> findByEnglishExercise_ExerciseId(Long exerciseId);
}
