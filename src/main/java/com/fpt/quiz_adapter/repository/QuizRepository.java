package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Status;
import com.fpt.quiz_adapter.entity.Visibility;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, Long> , JpaSpecificationExecutor<Quiz> {
    Optional<Quiz> findByQuizId(UUID quizId);
    List<Quiz> findTop8ByStatusAndVisibilityOrderByIdAsc(Status status, Visibility visibility);
    List<Quiz> findTop8ByIdGreaterThanAndStatusAndVisibilityOrderByIdAsc(Long id, Status status, Visibility visibility);
}
