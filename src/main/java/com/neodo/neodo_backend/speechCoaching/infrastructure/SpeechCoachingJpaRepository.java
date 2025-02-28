package com.neodo.neodo_backend.speechCoaching.infrastructure;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechCoachingJpaRepository extends JpaRepository<SpeechCoachingEntity, Long> {
}
