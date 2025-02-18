package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;

import java.util.Optional;

public interface SpeechCoachingRepository {
    Optional<SpeechCoachingEntity> findById(Long id);

}
