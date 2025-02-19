package com.neodo.neodo_backend.speechCoachings.dto.response;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;

public class SpeechCoachingChangeTextResponse {

    private Long speechCoachingFeedbackId;
    private Long speechCoachingId;
    private String title;

    public SpeechCoachingChangeTextResponse(Long speechCoachingFeedbackId, Long speechCoachingId, String title) {
        this.speechCoachingFeedbackId = speechCoachingFeedbackId;
        this.speechCoachingId = speechCoachingId;
        this.title = title;
    }

    public static SpeechCoachingChangeTextResponse from(SpeechCoachingFeedbackEntity speechCoachingFeedback){
        return new SpeechCoachingChangeTextResponse(speechCoachingFeedback.getSpeechBoardFeedbackId(), speechCoachingFeedback.getSpeechBoardId(), speechCoachingFeedback.getModifiedStt());
    }
}
