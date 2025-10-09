package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Visibility;
import com.fpt.quiz_adapter.repository.QuizRepository;
import com.fpt.quiz_adapter.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public Quiz getQuizById(UUID quizId) {
        return quizRepository.findById(quizId).orElse(null);
    }

    @Override
    public List<Quiz> getAllPublicQuiz() {
        return quizRepository.findAllByVisibility(Visibility.PUBLIC);
    }

    @Override
    public List<Quiz> getAllQuizByAdminId(Long adminId) {
        return quizRepository.findAllByCreateBy(adminId);
    }

    @Override
    public Quiz getQuizById(Long adminId, UUID quizId) {
        return quizRepository.findByIdAndCreateBy(quizId,adminId).orElse(null);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void editQuiz(UUID quizId, Quiz quiz) {
       quizRepository.updateQuizById(quizId, quiz.getTitle(), quiz.getDescription(),quiz.getVisibility());
    }

    @Override
    public void deleteQuizById(UUID quizId) {
        quizRepository.deleteById(quizId);
    }
}
