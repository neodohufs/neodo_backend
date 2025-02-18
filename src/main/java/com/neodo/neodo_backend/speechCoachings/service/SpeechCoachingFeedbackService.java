package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTextRequest;

public interface SpeechCoachingFeedbackService {
    void speechCoachingChangeText(Long speechCoachingId, SpeechCoachingChangeTextRequest request);
}
