package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "quizzes")
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    private Long createBy;
    private LocalDateTime createAt;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Question> questions;
}
