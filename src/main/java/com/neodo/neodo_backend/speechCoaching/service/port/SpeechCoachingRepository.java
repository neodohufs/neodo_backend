package com.neodo.neodo_backend.speechCoaching.service.port;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;

import java.util.Optional;

public interface SpeechCoachingRepository {
    SpeechCoachingEntity save(SpeechCoachingEntity speechCoachingEntity);
    Optional<SpeechCoachingEntity> findById(Long speechCoachingId);
}
