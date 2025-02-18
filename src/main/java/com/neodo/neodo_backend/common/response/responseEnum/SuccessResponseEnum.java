package com.neodo.neodo_backend.common.response.responseEnum;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessResponseEnum implements Response {
    READ_USER_INFO(HttpStatus.OK, "User Info Is Loaded Successfully"),
    READ_SPEECH_COACHING((HttpStatus.OK, "Speech Coaching List Is Loaded Successfully"),
    RESOURCES_CREATED(HttpStatus.CREATED, "Resourses created Successfully");

    private final HttpStatus httpStatus;
    private final String message;

}