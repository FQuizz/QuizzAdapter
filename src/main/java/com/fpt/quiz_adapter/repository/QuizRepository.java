package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizRepository extends JpaRepository<QuizEntity, UUID> {
}
