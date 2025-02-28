package com.neodo.neodo_backend.topic.dto.response;

import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TopicResponse {
    private Long topicId;
    private String topic;

    public static TopicResponse from(TopicEntity topicEntity) {
        return TopicResponse.builder()
                .topicId(topicEntity.getId())
                .topic(topicEntity.getTopic())
                .build();
    }
}

