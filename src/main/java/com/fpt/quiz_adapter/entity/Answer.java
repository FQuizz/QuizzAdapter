package com.fpt.quiz_adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    @Column(name = "answer_id")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToMany(mappedBy = "answers")
    private List<Choice> choices;
    private LocalDateTime answerAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private Attempt attempt;
}
