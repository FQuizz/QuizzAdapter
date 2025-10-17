package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Builder.Default
    private UUID choiceId = UUID.randomUUID();
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;
    @ManyToMany
    @JoinTable(name = "answer_choice",
        joinColumns = @JoinColumn(name = "choice_id"),
        inverseJoinColumns = @JoinColumn(name = "answer_id"))
    @JsonIgnore
    List<Answer> answers;
}
