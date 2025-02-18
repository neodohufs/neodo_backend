package com.neodo.neodo_backend.common.response.responseEnum;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResponseEnum implements Response {
    // Response 유효성 검사
    RESPONSE_NOT_VALID(HttpStatus.BAD_REQUEST, "Response Is Not Valid"),

    // auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized User"),
    AUTHENTICATION_IO_EXCEPTION(HttpStatus.BAD_REQUEST, "Client Send Bad Request"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Refresh Token Cannot Be Found"),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired JWT Token. Login Again Is Needed."),
    UNEXPECTED_AUTH_ERROR(HttpStatus.UNAUTHORIZED, "Unexpected Authentication Error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid Token"),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "Unsupported Token"),

    //duplicatedResources
    DUPLICATED_USERNAME(HttpStatus.CONFLICT , "Duplicated username"),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT , "Duplicated email"),




    //speech coaching 관련 에러
    SPEECH_COACHING_NOT_FOUND(HttpStatus.NOT_FOUND, "Speech Coaching Cannot Be Found"),
    SPEECH_COACHING_FEEDBACK_NOT_FOUND(HttpStatus.NOT_FOUND, "Speech Coaching Feedback Cannot Be Found"),
    INVALID_SPEECH_COACHING_ID(HttpStatus.BAD_REQUEST, "Invalid Speech Coaching ID");


    private final HttpStatus httpStatus;
    private final String message;
}

