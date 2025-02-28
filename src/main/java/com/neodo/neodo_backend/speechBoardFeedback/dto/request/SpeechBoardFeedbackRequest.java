package com.neodo.neodo_backend.speechBoardFeedback.dto.request;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SpeechBoardFeedbackRequest {
    private String record;
    private Atmosphere atmosphere;
    private Audience audience;
    private Scale scale;
    private Purpose purpose;
    private Long deadline;

    @Builder
    public SpeechBoardFeedbackRequest(String record, Atmosphere atmosphere, Audience audience, Scale scale, Purpose purpose, Long deadline) {
        this.record = record;
        this.atmosphere = atmosphere;
        this.audience = audience;
        this.scale = scale;
        this.purpose = purpose;
        this.deadline = deadline;
    }
}

