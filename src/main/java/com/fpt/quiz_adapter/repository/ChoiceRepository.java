package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice,Long> {
}
