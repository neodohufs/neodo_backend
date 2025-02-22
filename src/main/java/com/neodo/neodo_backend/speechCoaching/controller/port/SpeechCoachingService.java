package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.List;

public interface SpeechCoachingService {
    List<SpeechCoachingTopicResponse> get(UserEntity user);
}
