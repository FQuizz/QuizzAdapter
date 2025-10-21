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
    Quiz getQuizByQuizId(@PathVariable UUID quizId);
    @PostMapping("/create-quiz")
    Optional<Quiz> createQuiz(@RequestBody Quiz quiz);
    @PostMapping("/add-question/{quizId}/{questionId}")
    void addQuestion(@PathVariable UUID quizId, @PathVariable UUID questionId);
    @PutMapping("/update-quiz/{quizId}")
    Quiz updateQuiz(@PathVariable UUID quzId, @RequestBody Quiz quiz);
    @DeleteMapping("/delete-quiz/{quizId}")
    void deleteQuiz(@PathVariable UUID quizId);
    @DeleteMapping("/delete-question/{quizId}/{questionId}")
    void deleteQuestion(@PathVariable Long quizId, @PathVariable Long questionId);
}
