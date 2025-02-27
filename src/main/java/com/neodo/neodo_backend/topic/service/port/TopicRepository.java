package com.neodo.neodo_backend.topic.service.port;

import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;

import java.util.Optional;

public interface TopicRepository {
    void save(TopicEntity topicEntity);

    Optional<TopicEntity> findById(Long topicId);
}
