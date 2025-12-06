package com.example.elisaappbe.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data // Lombok
@Table(name = "englishVideo")
public class EnglishVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String youtubeId; // VD: dQw4w9WgXcQ
    private String title;
    private String level;
    private String type;

    // Quan hệ 1-N: Một video có nhiều dòng sub
    @OneToMany(mappedBy = "englishVideo", cascade = CascadeType.ALL)
    private List<EnglishSubtitle> subtitles;
}