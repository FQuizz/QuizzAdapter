package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Attempt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface AttemptService {
    @GetMapping("/attempts")
    List<Attempt> getAllAttempts();
    @GetMapping("/attempts/{attemptId}")
    Attempt getAttempt(@PathVariable UUID attemptId);
    @PostMapping("/attempts")
    Attempt createAttempt(@RequestBody Attempt attempt);
}
