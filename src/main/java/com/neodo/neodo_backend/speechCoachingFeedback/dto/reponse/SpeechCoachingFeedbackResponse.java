package com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse;

import lombok.Getter;

@Getter
public class SpeechCoachingFeedbackResponse {
    private String originalStt;
    private int score;
    private String conclusion;

}
