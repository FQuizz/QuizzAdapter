package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Answer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    @GetMapping("/attempts/{attemptId}/answers")
    List<Answer> getAnswerByAttemptId(@PathVariable  UUID attemptId);
    @PostMapping("/attempts/{attemptId}/answers")
    Answer createAnswer(@PathVariable UUID attemptId, @RequestBody Answer answer);
    @PutMapping("/answers/{answerId}")
    Answer editAnswer(@PathVariable UUID answerId, @RequestBody Answer answer);
    @DeleteMapping("/answers/{answerId}")
    void deleteAnswer(@PathVariable UUID answerId);
}
