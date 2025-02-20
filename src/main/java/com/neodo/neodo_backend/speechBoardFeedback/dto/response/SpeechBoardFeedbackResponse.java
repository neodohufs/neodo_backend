package com.neodo.neodo_backend.speechBoardFeedback.dto.response;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class SpeechBoardFeedbackResponse {
    private String originalStt;
    private int score;
    private String conclusion;
    private ArrayList<String> topics;
}
