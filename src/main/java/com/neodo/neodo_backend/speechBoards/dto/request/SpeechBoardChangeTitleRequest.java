package com.neodo.neodo_backend.speechBoards.dto.request;

import com.neodo.neodo_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SpeechBoardChangeTitleRequest {

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String title;

}
