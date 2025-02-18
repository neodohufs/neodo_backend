package com.neodo.neodo_backend.speechCoaching.service;

import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingResponse;
import com.neodo.neodo_backend.speechCoaching.service.port.SpeechCoachingRepository;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeechCoachingServiceImpl implements SpeechCoachingService {
    private final SpeechCoachingRepository speechCoachingRepository;
    private final TopicRepository topicRepository;

    @Override
    public SpeechCoachingResponse get(UserEntity user) {
        // TODO: 추후 예외처리 수정
        SpeechCoachingEntity speechCoaching = speechCoachingRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("SpeechCoaching not found for user: " + user.getId()));

        List<Topic> topics = topicRepository.findBySpeechCoachingId(speechCoaching.getId());

        return SpeechCoachingResponse.from(speechCoaching, topics);
    }
}

