package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Answer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerService {
    @GetMapping("/get-answers-in-attempt/{attemptId}")
    List<Answer> getAllAnswerInAttempt(@PathVariable UUID attemptId);
    @PostMapping("submit-answer/{attemptId}")
    Answer submitAnswer(@PathVariable UUID attemptId, @RequestBody Answer answer);
}
