package com.neodo.neodo_backend.speechBoardFeedback.controller.port;

import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;

public interface SpeechBoardFeedbackService {
    SpeechBoardFeedbackResponse getFeedback(Long speechBoardId);
}
