package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChoiceRepository extends JpaRepository<Choice, UUID> {
    @Query("SELECT c FROM Choice c WHERE c.question.id = ?1")
    List<Choice> findAllByQuestionId(UUID questionId);
}
