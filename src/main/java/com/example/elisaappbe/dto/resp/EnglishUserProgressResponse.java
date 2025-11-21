package com.example.elisaappbe.dto.resp;

import com.example.elisaappbe.model.EnglishLesson;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishUserProgressResponse {
    private long progressId;
    private long lessonId;
    private long section;
    private LocalDateTime lastAccessed;
}
