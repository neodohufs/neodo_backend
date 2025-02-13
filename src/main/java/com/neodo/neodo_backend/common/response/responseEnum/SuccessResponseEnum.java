package com.neodo.neodo_backend.common.response.responseEnum;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {
    // speech board
    GET_SPEECH_BOARD_FEEDBACK(HttpStatus.OK, "Speech Board Feedback Is Loaded Successfully");

    private final HttpStatus httpStatus;
    private final String message;

}