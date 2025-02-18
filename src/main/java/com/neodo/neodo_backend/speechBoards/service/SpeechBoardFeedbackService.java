package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTextRequest;

public interface SpeechBoardFeedbackService {
    void speechBoardChangeText(Long speechBoardId, SpeechBoardChangeTextRequest request);
}
