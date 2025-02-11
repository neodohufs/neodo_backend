package com.neodo.neodo_backend.security.dto;

import com.neodo.neodo_backend.common.constant.ValidationMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = ValidationMessage.RESPONSE_NOT_MATCH)
    private String email;

    @NotBlank(message = ValidationMessage.RESPONSE_NOT_BLANK)
    private String password;
}
