package com.neodo.neodo_backend.speechCoachingFeedback.controller.port;

import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingChangeTextResponse;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.request.SpeechCoachingChangeTextRequest;

public interface SpeechCoachingFeedbackService {
    SpeechCoachingFeedbackResponse getFeedback(Long speechCoachingId);
    SpeechCoachingChangeTextResponse speechCoachingChangeText(Long speechCoachingId, SpeechCoachingChangeTextRequest request);
}
