package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

public interface SpeechCoachingService {
    SpeechCoachingResponse get(UserEntity user);
}
