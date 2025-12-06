package com.example.elisaappbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "englishOrderingExercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishOrderingExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ExerciseID")
    @JsonIgnore
    private EnglishExercise englishExercise;

    @Column(name = "Title")
    private String title;

    @Column(name = "Hint")
    private String hint; // Hướng dẫn: "Sắp xếp lại câu chuyện sau"

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("correctOrder ASC") // Mặc định lấy ra theo thứ tự đúng
    private List<EnglishParagraphSegment> paragraphs;
}
