package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;

import java.util.Optional;

public interface SpeechCoachingFeedbackRepository {
    Optional<SpeechCoachingFeedbackEntity> findById(Long id);
    SpeechCoachingFeedbackEntity save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity);
}
