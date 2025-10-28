package com.fpt.quiz_adapter.spec;

import com.fpt.quiz_adapter.entity.Attempt;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class AttemptSpecification {
    private AttemptSpecification() {
    }

    public static Specification<Attempt> hasQuizId(UUID quizId) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("quiz").get("quizId"), quizId);
        };
    }

    public static Specification<Attempt> orderByScore() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("score")));
            return criteriaBuilder.conjunction();
        };
    }
}