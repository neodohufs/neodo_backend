package com.neodo.neodo_backend.common.response.responseEnum;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {
    READ_SPEECH_BOARD_LIST(HttpStatus.OK, "Speech Board List Is Loaded Successfully"),;

    private final HttpStatus httpStatus;
    private final String message;

}