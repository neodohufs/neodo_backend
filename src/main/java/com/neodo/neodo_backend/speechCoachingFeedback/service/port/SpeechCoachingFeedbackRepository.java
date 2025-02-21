package com.neodo.neodo_backend.speechCoachingFeedback.service.port;

import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;

public interface SpeechCoachingFeedbackRepository {

    void save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity);
}
