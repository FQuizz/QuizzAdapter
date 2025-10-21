package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Attempt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttemptService {
    @GetMapping("/get-all-attempts/{quizId}")
    List<Attempt> getAllAttempt(@PathVariable UUID quizId);
    @GetMapping("/get-attempt/{attemptId}")
    Optional<Attempt> getAttempt(@PathVariable UUID attemptId);
    @PostMapping("/create-attempt/{quizId}")
    Optional<Attempt> createAttempt(@PathVariable UUID quizId, @RequestBody Attempt attempt);
    @PutMapping("/finish-attempt/{attemptId}")
    Optional<Attempt> finishAttempt(@PathVariable UUID attemptId);
}
