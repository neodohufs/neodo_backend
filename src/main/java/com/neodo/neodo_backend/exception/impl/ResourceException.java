package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class ResourceException extends CustomException {

    public ResourceException(Response response) {
        super(response);
    }
}
