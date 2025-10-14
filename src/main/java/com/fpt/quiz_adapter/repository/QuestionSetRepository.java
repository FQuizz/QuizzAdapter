package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.QuestionSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface QuestionSetRepository extends JpaRepository<QuestionSet,Long>{
    @Modifying
    @Transactional
    @Query("DELETE FROM QuestionSet qs WHERE qs.quiz.id = ?1 AND qs.question.id = ?2")
    void deleteQuestionFromQuiz(Long quizId, Long questionId);
}
