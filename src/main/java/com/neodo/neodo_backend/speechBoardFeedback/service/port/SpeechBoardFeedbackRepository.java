package com.neodo.neodo_backend.speechBoardFeedback.service.port;

import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;

public interface SpeechBoardFeedbackRepository {
    void save(SpeechBoardFeedbackEntity speechBoardFeedbackEntity);
}
