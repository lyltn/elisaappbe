package com.example.elisaappbe.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "englishVocabularyTheory")
public class EnglishVocabularyTheory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VocabID")
    private Long vocabId;

    @ManyToOne
    @JoinColumn(name = "LessonID")
    private EnglishLesson englishLesson;

    @Column(name = "Word")
    private String word;

    @Column(name = "Meaning")
    private String meaning;

    @Column(name = "Type")
    private String type;

    @Column(name = "Ipa")
    private String ipa;

    @Column(name = "Example")
    private String example;

    @Column(name = "Image")
    private String image;

}

