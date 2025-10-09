package com.fpt.quiz_adapter.service.impl;

import com.fpt.quiz_adapter.entity.Choice;
import com.fpt.quiz_adapter.entity.Question;
import com.fpt.quiz_adapter.repository.ChoiceRepository;
import com.fpt.quiz_adapter.service.ChoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class ChoiceServiceImpl implements ChoiceService {
    private final ChoiceRepository choiceRepository;
    @Override
    public List<Choice> getAllChoices(UUID questionId) {
        return choiceRepository.findAllByQuestionId(questionId);
    }

    @Override
    public Choice addChoice(UUID questionId, Choice choice) {
        choice.setQuestion(Question.builder()
                .id(questionId)
            .build());
        return choiceRepository.save(choice);
    }

    @Override
    public Choice editChoice(UUID choiceId, Choice choice) {
        choiceRepository.findById(choiceId).ifPresent(c -> {
            Optional.ofNullable(choice.getContent()).ifPresent(c::setContent);
            Optional.ofNullable(choice.getIsCorrect()).ifPresent(c::setIsCorrect);
        });
        return null;
    }

    @Override
    public void deleteChoice(UUID choiceId) {
        choiceRepository.deleteById(choiceId);
    }
}
