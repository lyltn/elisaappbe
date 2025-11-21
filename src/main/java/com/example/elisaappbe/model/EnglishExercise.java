package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "englishExercise")
public class EnglishExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExerciseID")
    private Long exerciseId;

    @ManyToOne
    @JoinColumn(name = "LessonID")
    private EnglishLesson englishLesson;

    @Column(name = "ExerciseTitle")
    private String exerciseTitle;

    @Column(name = "ExerciseType")
    private String exerciseType;

    @Column(name = "ExerciseDescription")
    private String exerciseDescription;

    @OneToMany(mappedBy = "englishExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnglishSentenceRewritingQuestion> rewritingQuestions;

    @OneToMany(mappedBy = "englishExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnglishMultipleChoiceQuestion> multipleChoiceQuestions;
}