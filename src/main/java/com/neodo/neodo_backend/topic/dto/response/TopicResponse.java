package com.neodo.neodo_backend.topic.dto.response;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Getter
@AllArgsConstructor
@Builder
public class TopicResponse {
    private Long speechCoachingId;
    private Long topicId;
    private String topic;

    public static TopicResponse from(TopicEntity topicEntity, SpeechCoachingEntity speechCoachingEntity) {
        return TopicResponse.builder()
                .speechCoachingId(
                        Optional.ofNullable(speechCoachingEntity)
                                .map(SpeechCoachingEntity::getId).orElse(null))
                .topicId(topicEntity.getId())
                .topic(topicEntity.getTopic())
                .build();
    }
}

