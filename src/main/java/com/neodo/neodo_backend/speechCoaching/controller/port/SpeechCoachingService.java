package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingChangeTitleResponse;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpeechCoachingService {
    SpeechCoachingRecordResponseDto saveRecording(MultipartFile file, Long topicId);

    SpeechCoachingRecordResponseDto findRecording(Long speechCoachingId);

    List<SpeechCoachingTopicResponse> get(UserEntity user);

    SpeechCoachingChangeTitleResponse speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request);
}
