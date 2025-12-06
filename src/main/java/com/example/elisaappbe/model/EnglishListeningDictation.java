package com.example.elisaappbe.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "englishListeningDictation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishListeningDictation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Ví dụ: "Bài nghe số 1 - Tại sân bay"

    @Column(name = "audio_url", nullable = false)
    private String audioUrl; // Link file mp3 từ Cloudinary

    @Column(columnDefinition = "TEXT", nullable = false)
    private String transcript; // Nội dung chính xác cần chép lại

    // Gợi ý (nếu có), ví dụ: hiển thị chữ cái đầu
    @Column(name = "hint_text")
    private String hintText;

    @ManyToOne
    @JoinColumn(name = "ExerciseID")
    @JsonIgnore
    private EnglishExercise englishExercise;
}