package com.fpt.quiz_adapter.service;

import com.fpt.quiz_adapter.entity.Choice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChoiceService {
    @PostMapping("/create-choices/{questionId}")
    List<Choice> createChoices(@PathVariable Long questionId, @RequestBody List<Choice> choices);
    @PutMapping("/edit-choice/{questionId}")
    Optional<Choice> editChoice(@PathVariable Long questionId, @RequestBody List<Choice> choices);
    @DeleteMapping("delete-choice/{choiceId}")
    void deleteChoice(@PathVariable Long choiceId);
}
