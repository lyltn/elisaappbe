package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "englishGrammarTheory")
public class EnglishGrammarTheory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GrammarID")
    private Long grammarId;

    @ManyToOne
    @JoinColumn(name = "LessonID")
    private EnglishLesson englishLesson;

    @Column(name = "GrammarTitle")
    private String grammarTitle;

    @Column(name = "GrammarContent")
    private String grammarContent;

    @Column(name = "GrammarUsage")
    private String grammarUsage;

    @Column(name = "GrammarExample")
    private String grammarExample;

}

