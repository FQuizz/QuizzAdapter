package com.fpt.quiz_adapter.spec;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Status;
import com.fpt.quiz_adapter.entity.Visibility;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class QuizSpecification {
    private QuizSpecification() {}
    public static Specification<Quiz> isPublic(){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("visibility"),Visibility.PUBLIC);
        });
    }

    public static Specification<Quiz> isActive(){
        return (root, query, criteriaBuilder) -> {
           return criteriaBuilder.equal(root.get("status"),Status.ACTIVE);
        };
    }

    public static Specification<Quiz> hasQuizId(UUID quizId){
        return (root, query, criteriaBuilder) -> {
           return criteriaBuilder.equal(root.get("quizId"),quizId);
        };
    }

    public static Specification<Quiz> belongToAdmin(Long adminId){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder
                .and(criteriaBuilder
                    .equal(root.get("createBy"),adminId),
                    criteriaBuilder.notEqual(root.get("status"),Status.TERMINATE)
                );
        };
    };

    public static Specification<Quiz> notTerminate(){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.notEqual(root.get("status"),Status.TERMINATE);
        };
    }
}
