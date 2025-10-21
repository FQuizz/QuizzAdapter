package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Question;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public interface QuestionService {
    @GetMapping("/get-all-questions-in-quiz/{id}")
    List<Question> getQuestionsInQuiz(@PathVariable Long id);
    @GetMapping("/get-all-questions/{adminId}")
    List<Question> getAllQuestions(@PathVariable Long adminId);
    @GetMapping("/get-question/{questionId}")
    Question getQuestion(@PathVariable UUID questionId);
    @PostMapping("/create-question")
    Question createQuestion( @RequestBody Question question);
    @PutMapping("/update-question/{questionId}")
    Question updateQuestion(@PathVariable UUID questionId, @RequestBody Question question);
    @DeleteMapping("/delete-question/{questionId}")
    void deleteQuestion(@PathVariable UUID questionId);
}
