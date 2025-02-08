package com.neodo.neodo_backend.common.exception;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CommonResponse<?>> handleCustomException(CustomException e) {
        return ResponseEntity
                .status(e.getResponse().getHttpStatus())
                .body(CommonResponse.builder()
                        .response(e.getResponse())
                        .build());
    }
}
