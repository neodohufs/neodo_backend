package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTextRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTextResponse;

public interface SpeechCoachingFeedbackService {
    SpeechCoachingChangeTextResponse speechCoachingChangeText(Long speechCoachingId, SpeechCoachingChangeTextRequest request);
}
