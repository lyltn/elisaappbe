package com.example.elisaappbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "englishSubtitle")
public class EnglishSubtitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long startTime; // Lưu mili-giây (VD: 1500 = 1.5s)
    private Long endTime;

    @Column(length = 1000)
    private String content; // Nội dung tiếng Anh

    @ManyToOne
    @JoinColumn(name = "video_id")
    @JsonIgnore // Tránh vòng lặp vô tận khi xuất JSON
    private EnglishVideo englishVideo;
}