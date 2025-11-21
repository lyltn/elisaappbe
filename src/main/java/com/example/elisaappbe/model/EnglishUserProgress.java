package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "englishUserProgress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishUserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long progressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LessonID", nullable = false)
    private EnglishLesson englishLesson;

    @Column(name = "section")
    private Long section;

    @Column(name = "LastAccessed")
    private LocalDateTime lastAccessed;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", unique = true)
    private User user;

}
