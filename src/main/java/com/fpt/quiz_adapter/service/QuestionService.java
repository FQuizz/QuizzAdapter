package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
public interface QuestionService {
    @GetMapping("/quizzes/{quizId}/questions")
    List<Question> getAllQuestions(@PathVariable UUID quizId);
    @GetMapping("/questions/{questionId}")
    Question getQuestion(@PathVariable UUID questionId);
    @PostMapping("/quizzes/{quizId}/questions")
    Question addQuestion(@PathVariable UUID quizId, @RequestBody Question question);
    @PutMapping("/questions/{questionId}")
    Question editQuestion(@PathVariable UUID questionId, @RequestBody Question question);
    @DeleteMapping("/questions/{questionId}")
    void deleteQuestion(@PathVariable UUID questionId);
}
