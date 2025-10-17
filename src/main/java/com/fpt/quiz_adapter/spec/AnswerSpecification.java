package com.fpt.quiz_adapter.spec;

import com.fpt.quiz_adapter.entity.Answer;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class AnswerSpecification {
    public static Specification<Answer> belongToAttempt(UUID attemptId){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("atttempt.attempt.id"),attemptId);
        };
    }
}
