package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class AuthException extends CustomException {

    public AuthException(Response response) {
        super(response);
    }
}
