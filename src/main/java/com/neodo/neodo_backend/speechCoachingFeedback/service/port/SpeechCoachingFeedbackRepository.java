package com.neodo.neodo_backend.speechCoachingFeedback.service.port;

import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;

import java.util.Optional;

public interface SpeechCoachingFeedbackRepository {

    void save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity);
    Optional<SpeechCoachingFeedbackEntity> findBySpeechCoachingEntity_Id(Long speechCoachingId);
}
