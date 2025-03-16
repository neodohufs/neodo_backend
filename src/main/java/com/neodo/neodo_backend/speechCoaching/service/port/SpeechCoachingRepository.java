package com.neodo.neodo_backend.speechCoaching.service.port;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface SpeechCoachingRepository {
    SpeechCoachingEntity save(SpeechCoachingEntity speechCoachingEntity);
    Optional<SpeechCoachingEntity> findById(Long speechCoachingId);
    List<SpeechCoachingEntity> findByTopicEntityIn(List<TopicEntity> topicEntities);
}
