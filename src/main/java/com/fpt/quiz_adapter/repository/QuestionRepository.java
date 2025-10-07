package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
