package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
    @Id
    @Column(name = "question_id")
    private UUID id;
    private String content;
    private Boolean isActive;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private Integer questionPosition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @JsonIgnore
    private Quiz quiz;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Choice> choices;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answers;
}
