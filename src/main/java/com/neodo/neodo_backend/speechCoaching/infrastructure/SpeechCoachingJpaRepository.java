package com.neodo.neodo_backend.speechCoaching.infrastructure;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeechCoachingJpaRepository extends JpaRepository<SpeechCoachingEntity, Long> {
    Optional<SpeechCoachingEntity> findById(Long speechCoachingId);
}
