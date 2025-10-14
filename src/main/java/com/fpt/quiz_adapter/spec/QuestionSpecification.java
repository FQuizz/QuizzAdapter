package com.fpt.quiz_adapter.spec;

import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.entity.Status;
import org.springframework.data.jpa.domain.Specification;

public final class QuestionSpecification {
    private QuestionSpecification() {}
    public static Specification<Question> notTerminate(){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.notEqual(root.get("status"), Status.TERMINATE);
        };
    }

    public static Specification<Question> hasId(Long id){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("id"),id);
        };
    }

    public static Specification<Question> belongToAdmin(Long adminId){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("createBy"),adminId);
        };
    }
}
