package com.neodo.neodo_backend.speechBoardFeedback.dto.response;

import lombok.Getter;

@Getter
public class SpeechBoardFeedbackResponse {
    private String originalStt;
    private int score;
    private String conclusion;
}
