package com.neodo.neodo_backend.speechCoachingFeedback.controller.port;

import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;

public interface SpeechCoachingFeedbackService {
    SpeechCoachingFeedbackResponse getFeedback(Long speechCoachingId);
}
