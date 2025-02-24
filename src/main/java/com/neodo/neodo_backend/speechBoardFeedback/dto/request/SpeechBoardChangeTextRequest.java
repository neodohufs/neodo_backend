package com.neodo.neodo_backend.speechBoardFeedback.dto.request;

import com.neodo.neodo_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SpeechBoardChangeTextRequest {

    @NotNull(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private Long speechBoardFeedbackId;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String modified_stt;
}
