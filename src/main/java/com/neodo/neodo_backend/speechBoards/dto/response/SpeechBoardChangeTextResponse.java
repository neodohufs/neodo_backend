package com.neodo.neodo_backend.speechBoards.dto.response;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SpeechBoardChangeTextResponse {

    private Long speechBoardFeedbackId;
    private Long speechBoardId;
    private String modified_stt;

    public SpeechBoardChangeTextResponse(Long speechBoardFeedbackId, Long speechBoardId, String modified_stt) {
        this.speechBoardFeedbackId = speechBoardFeedbackId;
        this.speechBoardId = speechBoardId;
        this.modified_stt = modified_stt;
    }

    public static SpeechBoardChangeTextResponse from(SpeechBoardFeedbackEntity speechBoardFeedback){
        return new SpeechBoardChangeTextResponse(speechBoardFeedback.getSpeechBoardFeedbackId(), speechBoardFeedback.getSpeechBoardId(), speechBoardFeedback.getModifiedStt());
    }
}
