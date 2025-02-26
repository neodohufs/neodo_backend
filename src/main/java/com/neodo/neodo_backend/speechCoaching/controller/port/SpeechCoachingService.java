package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingRecordRequestDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface SpeechCoachingService {
    SpeechCoachingRecordResponseDto saveRecording(MultipartFile file);

    SpeechCoachingRecordRequestDto findrecording(Long id);
}
