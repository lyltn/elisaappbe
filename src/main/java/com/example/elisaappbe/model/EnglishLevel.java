package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "englishLevel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LevelID")
    private Long levelId;

    @Column(name = "LevelName")
    private String levelName;

    @Column(name = "Description")
    private String description;

    // Một Level có nhiều Lesson
    @OneToMany(mappedBy = "englishLevel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnglishLesson> englishLessons;
}
