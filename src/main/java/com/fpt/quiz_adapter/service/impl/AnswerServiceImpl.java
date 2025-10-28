package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Answer;
import com.fpt.quiz_adapter.entity.Choice;
import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.exception.NotFoundException;
import com.fpt.quiz_adapter.repository.AnswerRepository;
import com.fpt.quiz_adapter.repository.AttemptRepository;
import com.fpt.quiz_adapter.repository.ChoiceRepository;
import com.fpt.quiz_adapter.repository.QuestionRepository;
import com.fpt.quiz_adapter.service.AnswerService;
import com.fpt.quiz_adapter.spec.AnswerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AttemptRepository attemptRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    @Override
    public List<Answer> getAllAnswerInAttempt(UUID attemptId) {
        return answerRepository.findAll(AnswerSpecification.belongToAttempt(attemptId));
    }

    @Override
    @Transactional
    public Answer submitAnswer(UUID attemptId, Answer answer) {
        return attemptRepository.findByAttemptId(attemptId)
            .map(attempt -> {
                answer.setAttempt(attempt);
                answerRepository.save(answer);
                List<Choice> choices = answer.getChoices().stream()
                    .map(choice -> {
                        Choice _choice =  choiceRepository.findById(choice.getId())
                            .orElse(null);
                        _choice.getAnswers().add(answer);
                        return _choice;
                    })
                    .toList();
                answer.getChoices().addAll(choices);
                attempt.setScore(attempt.getScore() + answer.getPoint());
                attemptRepository.save(attempt);
                return answerRepository.save(answer);
            })
            .orElseThrow(() -> new NotFoundException("This attempt is not found"));
    }
}
