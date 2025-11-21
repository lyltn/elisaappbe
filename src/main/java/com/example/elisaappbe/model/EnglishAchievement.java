package com.example.elisaappbe.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "englishAchievements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AchievementsID")
    private Long achievementId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "min_xp", nullable = false)
    private Long minXp;

    @Column(name = "max_xp", nullable = false)
    private Long maxXp;

    @Column(name = "icon_url")
    private String iconUrl;
}