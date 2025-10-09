package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Quiz;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
public interface QuizService {
    @GetMapping("/quizzes/{quizId}")
    Quiz getQuizById(@PathVariable  UUID quizId);
    @GetMapping("/quizzes")
    List<Quiz> getAllPublicQuiz();
    @GetMapping("/admins/{adminId}/quizzes")
    List<Quiz> getAllQuizByAdminId(@PathVariable Long adminId);
    @GetMapping("/admins/{adminId}/quizzes/{quizId}")
    Quiz getQuizById(@PathVariable Long adminId, @PathVariable UUID quizId);
    @PostMapping("/quizzes")
    Quiz createQuiz(@RequestBody Quiz quiz);
    @PutMapping("/quizzes/{quizId}")
    void editQuiz(@PathVariable UUID quizId, @RequestBody Quiz quiz);
    @DeleteMapping("/quizzes/{quizId}")
    void deleteQuizById(@PathVariable UUID quizId);
}
