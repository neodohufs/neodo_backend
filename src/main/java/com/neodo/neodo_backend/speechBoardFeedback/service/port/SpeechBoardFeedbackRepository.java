package com.neodo.neodo_backend.speechBoardFeedback.service.port;

import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;

import java.util.Optional;

public interface SpeechBoardFeedbackRepository {
    void save(SpeechBoardFeedbackEntity speechBoardFeedbackEntity);
    Optional<SpeechBoardFeedbackEntity> findById(Long speechBoardFeedbackId);
}
