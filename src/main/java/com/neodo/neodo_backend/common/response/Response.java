package com.neodo.neodo_backend.common.response;

import org.springframework.http.HttpStatus;

public interface Response {
    HttpStatus getHttpStatus();
    String getMessage();
}