package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "choices")
public class Choice {
    @Id
    @Column(name = "choice_id")
    private UUID id;
    private String content;
    private Boolean isCorrect;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
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
