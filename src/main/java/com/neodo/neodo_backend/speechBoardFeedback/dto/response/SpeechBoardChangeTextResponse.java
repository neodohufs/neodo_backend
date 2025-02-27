package com.neodo.neodo_backend.speechBoardFeedback.dto.response;

import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;

import lombok.Getter;

@Getter
public class SpeechBoardChangeTextResponse {

    private Long speechBoardFeedbackId;
    private Long speechBoardId;
    private String modifiedStt;

    public SpeechBoardChangeTextResponse(Long speechBoardFeedbackId, Long speechBoardId, String modifiedStt) {
        this.speechBoardFeedbackId = speechBoardFeedbackId;
        this.speechBoardId = speechBoardId;
        this.modifiedStt = modifiedStt;
    }

    public static SpeechBoardChangeTextResponse from(SpeechBoardFeedbackEntity speechBoardFeedback){
        return new SpeechBoardChangeTextResponse(speechBoardFeedback.getId(), speechBoardFeedback.getSpeechBoardEntity().getId(), speechBoardFeedback.getModifiedStt());
    }
}
