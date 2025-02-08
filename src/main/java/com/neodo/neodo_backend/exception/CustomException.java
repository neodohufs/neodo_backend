package com.neodo.neodo_backend.exception;

import com.neodo.neodo_backend.common.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException{
    private final Response response;
}