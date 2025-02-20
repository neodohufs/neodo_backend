package com.neodo.neodo_backend.speechBoardFeedback.dto.request;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Formality;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.SpeechType;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.AudienceSize;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SpeechBoardFeedbackRequest {
    private String record;
    private Formality formality;
    private Audience audienceLevel;
    private AudienceSize audienceSize;
    private SpeechType speechType;
    private Long deadline;

    @Builder
    public SpeechBoardFeedbackRequest(String record, Formality formality, Audience audienceLevel, AudienceSize audienceSize, SpeechType speechType, Long deadline) {
        this.record = record;
        this.formality = formality;
        this.audienceLevel = audienceLevel;
        this.audienceSize = audienceSize;
        this.speechType = speechType;
        this.deadline = deadline;
    }
}

