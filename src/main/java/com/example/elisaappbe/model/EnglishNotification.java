package com.example.elisaappbe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "englishNotification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnglishNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationID")
    private Long notificationId;

    @Column(name = "Title")
    private String title;

    @Column(name = "Content")
    private String content;

    @Column(name = "ImageURL")
    private String imageUrl;

    @Column(name = "SendTime")
    private LocalDateTime sendTime;

    @Column(name = "Type")
    private String type;

    @Column(name = "IsRead")
    private Boolean isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    @JsonIgnore                        // RẤT QUAN TRỌNG: Tránh lỗi vòng lặp vô hạn khi API trả về JSON
    private User user;
}
