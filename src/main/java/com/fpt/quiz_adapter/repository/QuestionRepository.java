package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface QuestionRepository extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Question> {
    @Query("SELECT q FROM Question q JOIN q.belongedQuestionSet qs ON q.id = qs.question.id WHERE qs.quiz.id = ?1 and q.status = 'ACTIVE' ORDER BY qs.position")
    List<Question> findQuestionInQuiz(Long id);
}
