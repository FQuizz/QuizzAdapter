package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Choice;
import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.entity.Status;
import com.fpt.quiz_adapter.repository.QuestionRepository;
import com.fpt.quiz_adapter.service.QuestionService;
import com.fpt.quiz_adapter.spec.QuestionSpecification;
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
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    @Override
    public List<Question> getQuestionsInQuiz(Long id) {
        return questionRepository.findQuestionInQuiz(id);
    }

    @Override
    public List<Question> getAllQuestions(Long adminId) {
        return questionRepository
            .findAll(Specification
                .allOf(QuestionSpecification.notTerminate(),QuestionSpecification.belongToAdmin(adminId)));
    }

    @Override
    public Optional<Question> createQuestion( Question question) {
        question.getChoices().forEach(choice -> choice.setQuestion(question));
        return Optional.of(questionRepository.save(question));
    }

    @Override
    public Optional<Question> updateQuestion(UUID questionId, Question question) {
        return questionRepository
            .findOne(Specification.
                allOf(QuestionSpecification.notTerminate(),QuestionSpecification.hasQuestionId(questionId)))
            .map(q -> {
                Optional.ofNullable(question.getContent()).ifPresent(q::setContent);
                Optional.ofNullable(question.getQuestionType()).ifPresent(q::setQuestionType);
                Optional.ofNullable(question.getPoint()).ifPresent(q::setPoint);
                Optional.ofNullable(question.getTimeLimit()).ifPresent(q::setTimeLimit);
                Optional.ofNullable(question.getFact()).ifPresent(q::setFact);
                Optional.ofNullable(question.getChoices()).ifPresent(choices -> {
                    q.getChoices().clear();
                    choices.forEach( choice -> choice.setQuestion(q));
                    q.getChoices().addAll(choices);
                });
                return questionRepository.save(q);
            });
    }

    @Override
    public void deleteQuestion(UUID questionId) {
        questionRepository
            .findOne(Specification
                .allOf(QuestionSpecification.notTerminate(),QuestionSpecification.hasQuestionId(questionId)))
            .ifPresent(question -> {
                question.setStatus(Status.TERMINATE);
                questionRepository.save(question);
            });
    }
}
