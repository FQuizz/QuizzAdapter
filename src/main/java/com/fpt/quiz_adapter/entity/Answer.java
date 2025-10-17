package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Builder.Default
    private UUID answerId = UUID.randomUUID();
    @Column(nullable = false)
    private Long point;
    @Column(nullable = false)
    private Integer accuracyFactor;
    @Enumerated(EnumType.STRING)
    private AnswerResult result;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Question question;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Attempt attempt;
    @ManyToMany(mappedBy = "answers")
    private List<Choice> choices;
    @JsonGetter("question")
    public UUID getQuestionId(){
        return this.question.getQuestionId();
    }
}
