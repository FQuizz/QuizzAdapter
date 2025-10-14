package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.entity.QuestionSet;
import com.fpt.quiz_adapter.entity.Quiz;
import com.fpt.quiz_adapter.entity.Status;
import com.fpt.quiz_adapter.repository.QuestionSetRepository;
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
    private final QuestionSetRepository questionSetRepository;
    @Override
    public List<Quiz> getAllPublicQuizzes() {
        return quizRepository
            .findAll(Specification
                .allOf(QuizSpecification.isPublic(),QuizSpecification.isActive()));
    }

    @Override
    public List<Quiz> getAllQuizzes(Long adminId) {
        return quizRepository
            .findAll(Specification
                .allOf(QuizSpecification.notTerminate(),QuizSpecification.belongToAdmin(adminId)));
    }

    @Override
    public Optional<Quiz> getQuizByQuizId(UUID quizId) {
        return quizRepository
            .findOne(Specification
                .allOf(QuizSpecification.notTerminate(),QuizSpecification.hasQuizId(quizId)));
    }

    @Override
    public Optional<Quiz> createQuiz( Quiz quiz){
        return Optional.of(quizRepository.save(quiz));
    }

    @Override
    public void addQuestion(Long quizId, Long questionId) {
        quizRepository.findById(quizId)
            .ifPresent(quiz -> {
                Question question = Question.builder()
                    .id(questionId)
                    .build();
                questionSetRepository.save(QuestionSet.builder()
                        .quiz(quiz)
                        .question(question)
                        .position(quiz.getTotalQuestion())
                    .build());
                quiz.setTotalQuestion(quiz.getTotalQuestion() + 1);
                quizRepository.save(quiz);
            });
    }

    @Override
    public Optional<Quiz> updateQuiz(UUID quizId, Quiz quiz) {
    return quizRepository
        .findOne(Specification
            .allOf(QuizSpecification.notTerminate(),QuizSpecification.hasQuizId(quizId)))
            .map(updatedQuiz -> {
                Optional.ofNullable(quiz.getTitle()).ifPresent(updatedQuiz::setTitle);
                Optional.ofNullable((quiz.getDescription())).ifPresent(updatedQuiz::setDescription);
                Optional.ofNullable(quiz.getVisibility()).ifPresent(updatedQuiz::setVisibility);
                return quizRepository.save(updatedQuiz);
            });
    }

    @Override
    public void deleteQuiz(UUID quizId) {
        quizRepository.findOne(Specification
                .allOf(QuizSpecification.hasQuizId(quizId),QuizSpecification.notTerminate()))
            .ifPresent(quiz -> {
                quiz.setStatus(Status.TERMINATE);
                quizRepository.save(quiz);
            });
    }

    @Override
    public void deleteQuestion(Long quizId, Long questionId) {
        questionSetRepository.deleteQuestionFromQuiz(quizId,questionId);
    }
}
