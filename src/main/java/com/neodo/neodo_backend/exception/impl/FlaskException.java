package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class FlaskException extends CustomException {

    public FlaskException(Response response) {
        super(response);
    }
}
