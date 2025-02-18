package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;

import java.util.Optional;

public interface SpeechBoardFeedbackRepository {
    Optional<SpeechBoardFeedbackEntity> findById(Long speechBoardFeedbackId);
}
