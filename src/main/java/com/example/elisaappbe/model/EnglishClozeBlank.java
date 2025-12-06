package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "englishClozeBlanks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishClozeBlank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer blankIndex; // Số thứ tự của ô trống (ví dụ: 1, 2)

    private String correctAnswer; // Đáp án đúng (ví dụ: "market")

    // (Tuỳ chọn) Gợi ý cho người dùng nếu bí
    private String hint;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @JsonIgnore // Tránh vòng lặp vô tận khi convert sang JSON
    private EnglishClozeExercise exercise;
}