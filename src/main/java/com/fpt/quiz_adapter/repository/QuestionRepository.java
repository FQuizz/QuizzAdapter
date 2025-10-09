package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    @Query("SELECT q FROM Question q where q.quiz.id = ?1")
    List<Question> findAllByQuizId(UUID quizId);
}
