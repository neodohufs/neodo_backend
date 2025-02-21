package com.neodo.neodo_backend.speechCoachingFeedback.dto.request;

import lombok.Builder;

public class SpeechCoachingFeedbackRequest {
    private String record;

    @Builder
    public SpeechCoachingFeedbackRequest(String record) {
        this.record = record;
    }
}
