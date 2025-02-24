package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingRecordRequestDto;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SpeechCoachingService {
    SpeechCoachingRecordRequestDto saveRecording(SpeechCoachingRecordRequestDto request, MultipartFile file, UserEntity user);
}
