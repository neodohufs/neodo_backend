package com.neodo.neodo_backend.speechCoachings.dto.response;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;

public class SpeechCoachingChangeTextResponse {

    private Long speechCoachingFeedbackId;
    private Long speechCoachingId;
    private String modified_stt;

    public SpeechCoachingChangeTextResponse(Long speechCoachingFeedbackId, Long speechCoachingId, String modified_stt) {
        this.speechCoachingFeedbackId = speechCoachingFeedbackId;
        this.speechCoachingId = speechCoachingId;
        this.modified_stt = modified_stt;
    }

    public static SpeechCoachingChangeTextResponse from(SpeechCoachingFeedbackEntity speechCoachingFeedback){
        return new SpeechCoachingChangeTextResponse(speechCoachingFeedback.getSpeechBoardFeedbackId(), speechCoachingFeedback.getSpeechBoardId(), speechCoachingFeedback.getModifiedStt());
    }
}
