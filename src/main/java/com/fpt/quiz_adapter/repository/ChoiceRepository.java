package com.fpt.quiz_adapter.repository;

import com.fpt.quiz_adapter.entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChoiceRepository extends JpaRepository<ChoiceEntity, UUID> {
}
