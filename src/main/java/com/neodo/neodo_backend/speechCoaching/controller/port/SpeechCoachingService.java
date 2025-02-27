package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.List;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface SpeechCoachingService {
    SpeechCoachingRecordResponseDto saveRecording(MultipartFile file, Long topicId);

    SpeechCoachingRecordResponseDto findRecording(Long speechCoachingId);

    List<SpeechCoachingTopicResponse> get(UserEntity user);
}
