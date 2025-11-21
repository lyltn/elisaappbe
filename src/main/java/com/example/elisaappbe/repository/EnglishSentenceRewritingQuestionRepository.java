package com.example.elisaappbe.repository;

import com.example.elisaappbe.model.EnglishSentenceRewritingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnglishSentenceRewritingQuestionRepository extends JpaRepository<EnglishSentenceRewritingQuestion, Long> {
    List<EnglishSentenceRewritingQuestion> findByEnglishExercise_ExerciseId(Long exerciseId);
}
