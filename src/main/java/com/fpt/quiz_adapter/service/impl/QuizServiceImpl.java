package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.repository.QuizRepository;
import com.fpt.quiz_adapter.spec.QuizSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class QuizServiceImpl implements QuizService{
    private final QuizRepository quizRepository;
    @Override
    public List<Quiz> getAllPublicQuiz() {
        return quizRepository.findAll(QuizSpecification.isActive());
    }
}
