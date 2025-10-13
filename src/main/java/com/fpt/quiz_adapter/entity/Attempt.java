package com.fpt.quiz_adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Attempts")
public class Attempt {
    @Id
    @Column(name = "attempt_id")
    private UUID id;
    private String username;
    private LocalDateTime startAt;
    private LocalDateTime completeAt;
    private Integer score;
    @OneToMany(mappedBy = "attempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
}
