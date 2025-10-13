package com.fpt.quiz_adapter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private Integer questionPosition;
    private Integer point;
    private Integer timeLimit;
    @CreatedBy
    private Long createBy;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedBy
    private Long modifiedBy;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private QuestionSet belongedQuestionSet;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Choice> choices;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answers;
}
