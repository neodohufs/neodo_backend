package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class SpeechBoardException extends CustomException {
    public SpeechBoardException(Response response) {
        super(response);
    }
}
