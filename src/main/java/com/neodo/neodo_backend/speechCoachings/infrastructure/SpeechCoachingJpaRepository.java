package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechCoachingJpaRepository extends JpaRepository<SpeechCoachingEntity, Long> {
}
