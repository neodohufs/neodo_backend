package com.neodo.neodo_backend.speechCoaching.service;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.topic.service.port.TopicRepository;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpeechCoachingServiceImpl implements SpeechCoachingService {
    private final SpeechBoardRepository speechBoardRepository;
    private final TopicRepository topicRepository;

    @Override
    public List<SpeechCoachingTopicResponse> get(UserEntity user) {
        List<SpeechBoardEntity> speechBoardEntities = speechBoardRepository.findByUserId(user.getId());
        List<TopicEntity> topicEntities = topicRepository.findBySpeechBoardEntityIn(speechBoardEntities);

        Map<Long, List<TopicEntity>> topicsByBoardId = topicEntities.stream()
                .collect(Collectors.groupingBy(topic -> topic.getSpeechBoardEntity().getId()));

        return speechBoardEntities.stream()
                .map(speechBoard -> SpeechCoachingTopicResponse.from(
                        speechBoard,
                        topicsByBoardId.getOrDefault(speechBoard.getId(), Collections.emptyList()) // 해당 보드에 토픽이 없으면 빈 리스트 반환
                ))
                .collect(Collectors.toList());
    }
}

