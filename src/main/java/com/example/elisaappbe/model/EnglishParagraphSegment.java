package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "englishParagraphSegments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishParagraphSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; // Nội dung của đoạn văn/câu này

    private Integer correctOrder; // Thứ tự đúng: 1, 2, 3, 4

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnore
    private EnglishOrderingExercise exercise;
}