package com.neodo.neodo_backend.speechCoaching.controller.port;

import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface SpeechCoachingService {
    SpeechCoachingRecordResponseDto saveRecording(MultipartFile file, Long topicId);

    SpeechCoachingRecordResponseDto findRecording(Long speechCoachingId);
}
