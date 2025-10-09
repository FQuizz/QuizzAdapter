package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Visibility;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findAllByVisibility(Visibility visibility);
    List<Quiz> findAllByCreateBy(Long adminId);
    Optional<Quiz> findByIdAndCreateBy(UUID quizId,Long adminId);
    @Modifying
    @Transactional
    @Query("UPDATE Quiz q SET q.title = ?2, q.description = ?3, q.visibility = ?4 WHERE q.id = ?1")
    void updateQuizById(UUID quizId, String title, String description, Visibility visibility);
}
