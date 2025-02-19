package com.neodo.neodo_backend.speechCoachings.dto.request;

import com.neodo.neodo_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SpeechCoachingChangeTitleRequest {

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }
}
