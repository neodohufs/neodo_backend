package com.neodo.neodo_backend.exception.impl;

import com.neodo.neodo_backend.common.response.Response;
import com.neodo.neodo_backend.exception.CustomException;

public class SpeechCoachingException extends CustomException {
    public SpeechCoachingException(Response response) {
        super(response);
    }
}
