package com.example.elisaappbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "englishClozeExercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishClozeExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Ví dụ: "Daily Routine"

    @Lob // Để lưu đoạn văn dài
    @Column(columnDefinition = "TEXT")
    private String content;
    // Ví dụ lưu trong DB: "Yesterday, I went to the {{1}} to buy some {{2}}."

    // Quan hệ 1-n với các ô trống
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EnglishClozeBlank> blanks;

    @ManyToOne
    @JoinColumn(name = "ExerciseID")
    @JsonIgnore
    private EnglishExercise englishExercise;
}
