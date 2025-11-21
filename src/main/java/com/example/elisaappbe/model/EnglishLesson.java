package com.example.elisaappbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "englishLesson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LessonID")
    private Long lessonId;

    @Column(name = "LessonName")
    private String lessonName;

    @Column(name = "LessonDescription")
    private String lessonDescription;

    @Column(name = "TypeLesson")
    private String typeLesson;

    @OneToMany(mappedBy = "englishLesson", cascade = CascadeType.ALL)
    private List<EnglishVocabularyTheory> englishVocabularies;

    @OneToMany(mappedBy = "englishLesson", cascade = CascadeType.ALL)
    private List<EnglishGrammarTheory> englishGrammars;

    @OneToMany(mappedBy = "englishLesson", cascade = CascadeType.ALL)
    private List<EnglishExercise> englishExercises;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LevelID", referencedColumnName = "LevelID")
    private EnglishLevel englishLevel;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
//    private User user;

}
