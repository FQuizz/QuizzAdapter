package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttemptRepository extends JpaRepository<Attempt, Long>, JpaSpecificationExecutor<Attempt> {
    Optional<Attempt> findByAttemptId(UUID attemptId);

}
