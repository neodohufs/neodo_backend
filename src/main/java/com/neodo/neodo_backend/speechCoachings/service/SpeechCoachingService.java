package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTitleResponse;

public interface SpeechCoachingService {
    SpeechCoachingChangeTitleResponse speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request);
}
