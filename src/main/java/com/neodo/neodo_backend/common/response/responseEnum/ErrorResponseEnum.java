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
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource Cannot Be Found"),

    // flask, S3
    EXTERNAL_SERVICE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server of External Service Error"),

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

    //Speechboard Recording

    RECORDING_NOT_FOUND(HttpStatus.NOT_FOUND, "Recording File Cannot Be Found");

    private final HttpStatus httpStatus;
    private final String message;
}

