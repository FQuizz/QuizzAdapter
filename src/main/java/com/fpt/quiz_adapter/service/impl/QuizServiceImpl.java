package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Status;
import com.fpt.quiz_adapter.repository.QuizRepository;
import com.fpt.quiz_adapter.service.QuizService;
import com.fpt.quiz_adapter.spec.QuizSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@ResponseBody
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    @Override
    public List<Quiz> getAllPublicQuizzes() {
        return quizRepository
            .findAll(Specification
                .allOf(QuizSpecification.isPublic(),QuizSpecification.isActive()));
    }

    @Override
    public List<Quiz> getAllQuizzes(Long adminId) {
        return quizRepository
            .findAll(QuizSpecification.belongToAdmin(adminId));
    }

    @Override
    public Optional<Quiz> getQuizByQuizId(UUID quizId) {
        return quizRepository
            .findOne(Specification
                .allOf(QuizSpecification.isActive(),QuizSpecification.hasQuizId(quizId)));
    }

    @Override
    public Optional<Quiz> createQuiz(Long adminId, Quiz quiz){
        Quiz createdQuiz = Quiz.builder()
            .title(quiz.getTitle())
            .description(quiz.getDescription())
            .visibility(quiz.getVisibility())
            .quizId(UUID.randomUUID())
            .createBy(adminId)
            .modifiedBy(adminId)
            .build();
        return Optional.of(quizRepository.save(createdQuiz));
    }

    @Override
    public Optional<Quiz> updateQuiz(UUID quizId, Quiz quiz) {
    return quizRepository
        .findOne(Specification
            .allOf(QuizSpecification.isActive(),QuizSpecification.hasQuizId(quizId)))
            .map(updatedQuiz -> {
                Optional.ofNullable(quiz.getTitle()).ifPresent(updatedQuiz::setTitle);
                Optional.ofNullable((quiz.getDescription())).ifPresent(updatedQuiz::setDescription);
                Optional.ofNullable(quiz.getVisibility()).ifPresent(updatedQuiz::setVisibility);
                return quizRepository.save(updatedQuiz);
            });
    }

    @Override
    public void deleteQuiz(UUID quizId) {
        quizRepository.findOne(QuizSpecification.hasQuizId(quizId))
            .ifPresent(quiz -> {
                quiz.setStatus(Status.TERMINATE);
                quizRepository.save(quiz);
            });
    }
}
