package com.neodo.neodo_backend.speechCoaching.service.port;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;

import java.util.Optional;

public interface SpeechCoachingRepository {
    Optional<SpeechCoachingEntity> findById(Long speechCoachingId);
}
