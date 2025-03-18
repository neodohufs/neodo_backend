package com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeechCoachingFeedbackResponse {
    private String originalStt;
    private int score;
    private String conclusion;
}
