package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Answer;
import com.fpt.quiz_adapter.entity.Attempt;
import com.fpt.quiz_adapter.entity.AttemptStatus;
import com.fpt.quiz_adapter.exception.ConflictException;
import com.fpt.quiz_adapter.repository.AttemptRepository;
import com.fpt.quiz_adapter.repository.QuizRepository;
import com.fpt.quiz_adapter.service.AttemptService;
import com.fpt.quiz_adapter.spec.AttemptSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final QuizRepository quizRepository;
    @Override
    public List<Attempt> getAllAttempt(UUID quizId) {
        return attemptRepository.findAll(AttemptSpecification.hasQuizId(quizId));
    }

    @Override
    public Optional<Attempt> getAttempt(UUID attemptId) {
        return attemptRepository.findByAttemptId(attemptId);
    }

    @Override
    public Optional<Attempt> createAttempt(UUID quizId, Attempt attempt) {
        attemptRepository.findByUsername(attempt.getUsername())
            .ifPresent( _attempt -> {
                throw new ConflictException("this username has already existed");
            });
        return quizRepository.findByQuizId(quizId)
            .map(quiz -> {
                attempt.setQuiz(quiz);
                return attemptRepository.save(attempt);
            });
    }

    @Override
    public Optional<Attempt> finishAttempt(UUID attemptId) {
        return attemptRepository.findByAttemptId(attemptId)
            .map(attempt -> {
                attempt.setAttemptStatus(AttemptStatus.COMPLETED);
                attempt.setCompleteAt(LocalDateTime.now());
                Long totalScore = attempt.getAnswers()
                    .stream()
                    .mapToLong(Answer::getPoint)
                    .sum();
                attempt.setScore(totalScore);
                return attemptRepository.save(attempt);
            });
    }
}
