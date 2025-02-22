package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class ExternalServiceException extends CustomException {

    public ExternalServiceException(Response response) {
        super(response);
    }
}
