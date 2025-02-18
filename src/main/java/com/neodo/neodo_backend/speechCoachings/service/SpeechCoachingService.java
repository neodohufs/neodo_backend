package com.neodo.neodo_backend.speechCoachings.service;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;

public interface SpeechCoachingService {

    void speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request);
}
