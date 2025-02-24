package com.neodo.neodo_backend.speechBoardFeedback.controller.port;

import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardChangeTextResponse;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;

public interface SpeechBoardFeedbackService {
    SpeechBoardFeedbackResponse getFeedback(Long speechBoardId);
    SpeechBoardChangeTextResponse speechBoardChangeText(Long speechBoardId, SpeechBoardChangeTextRequest request);

}
