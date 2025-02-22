package com.neodo.neodo_backend.topic.service.port;

import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;

public interface TopicRepository {
    void save(TopicEntity topicEntity);
}
