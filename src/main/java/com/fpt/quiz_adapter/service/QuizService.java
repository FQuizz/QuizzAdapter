package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Quiz;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuizService {
    @GetMapping("/get-all-public-quizzes")
    List<Quiz> getAllPublicQuizzes();
    @GetMapping("/get-all-quizzes/{adminId}")
    List<Quiz> getAllQuizzes(@PathVariable Long adminId);
    @GetMapping("/get-quiz-by-quiz-id/{quizId}")
    Optional<Quiz> getQuizByQuizId(@PathVariable UUID quizId);
    @PostMapping("/create-quiz/{adminId}")
    Optional<Quiz> createQuiz(@PathVariable Long adminId, @RequestBody Quiz quiz);
    @PutMapping("/update-quiz/{quizId}")
    Optional<Quiz> updateQuiz(@PathVariable UUID quzId, @RequestBody Quiz quiz);
    @DeleteMapping("/delete-quiz/{quizId}")
    void deleteQuiz(@PathVariable UUID quizId);
}
