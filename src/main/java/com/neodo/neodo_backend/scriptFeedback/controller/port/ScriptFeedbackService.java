package com.neodo.neodo_backend.scriptFeedback.controller.port;

import com.neodo.neodo_backend.scriptFeedback.dto.response.ScriptFeedbackResponse;

public interface ScriptFeedbackService {
    ScriptFeedbackResponse getFeedback(Long scriptId);
}
