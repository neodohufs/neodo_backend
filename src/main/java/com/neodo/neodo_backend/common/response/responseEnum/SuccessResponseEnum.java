package com.neodo.neodo_backend.common.response.responseEnum;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {
    // speech board
    GET_SPEECH_BOARD_FEEDBACK(HttpStatus.OK, "Speech Board Feedback Is Loaded Successfully"),

    // speech coaching
    GET_SPEECH_COACHING_FEEDBACK(HttpStatus.OK, "Speech Coaching Feedback Is Loaded Successfully"),

    READ_USER_INFO(HttpStatus.OK, "User Info Is Loaded Successfully"),
    RESOURCES_CREATED(HttpStatus.CREATED, "Resourses created Successfully"),
    RESOURCES_GET(HttpStatus.CREATED, "Resourses is got Successfully");

    private final HttpStatus httpStatus;
    private final String message;

}