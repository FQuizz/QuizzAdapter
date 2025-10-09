package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.repository.QuestionRepository;
import com.fpt.quiz_adapter.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    @Override
    public List<Question> getAllQuestions(UUID quizId) {
        return questionRepository.findAllByQuizId(quizId);
    }

    @Override
    public Question getQuestion(UUID questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }

    @Override
    public Question addQuestion(UUID quizId, Question question) {
        question.setQuiz(Quiz.builder()
                .id(quizId)
            .build());
        return questionRepository.save(question);
    }

    @Override
    public Question editQuestion(UUID questionId, Question question) {
        questionRepository.findById(questionId).ifPresent(q -> {
            Optional.ofNullable(question.getContent()).ifPresent(q::setContent);
            Optional.ofNullable(question.getIsActive()).ifPresent(q::setIsActive);
            Optional.ofNullable(question.getQuestionType()).ifPresent(q::setQuestionType);
        });
        return null;
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        questionRepository.deleteById(questionId);
    }
}
