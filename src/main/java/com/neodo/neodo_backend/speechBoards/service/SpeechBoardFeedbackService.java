package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTextResponse;

public interface SpeechBoardFeedbackService {
    SpeechBoardChangeTextResponse speechBoardChangeText(Long speechBoardId, SpeechBoardChangeTextRequest request);
}
