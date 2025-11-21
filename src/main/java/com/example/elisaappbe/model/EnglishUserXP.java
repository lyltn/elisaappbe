package com.example.elisaappbe.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "englishUserXP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishUserXP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserXPID")
    private Long userXPId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AchievementsID")
    private EnglishAchievement currentAchievement;

    @Column(name = "total_xp", nullable = false)
    private Long totalXP;
}
