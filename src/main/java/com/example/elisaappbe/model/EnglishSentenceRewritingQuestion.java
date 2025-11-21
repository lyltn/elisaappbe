package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "englishSentenceRewritingQuestion")
public class EnglishSentenceRewritingQuestion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "QuestionID")
    private long questionID;

    @ManyToOne
    @JoinColumn (name = "ExerciseID")
    private EnglishExercise englishExercise;

    @Column (name = "OriginalSentence")
    private String originalSentence;

    @Column (name = "RewrittenSentence")
    private String rewrittenSentence;

    @Column (name = "Hint")
    private String hint;

    @Column(name = "LinkMedia")
    private String linkMedia;

}
