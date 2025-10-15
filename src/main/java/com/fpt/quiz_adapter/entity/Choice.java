package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID choiceId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonIgnore
    private Question question;
    @ManyToMany
    @JoinTable(name = "answer_choice", joinColumns = {
        @JoinColumn(name = "answer_id"),
    },inverseJoinColumns = {
        @JoinColumn(name = "choice_id")
    })
    @JsonIgnore
    private List<Answer> answers;
}
