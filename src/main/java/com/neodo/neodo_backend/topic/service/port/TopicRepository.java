package com.neodo.neodo_backend.topic.service.port;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;

import java.util.List;

public interface TopicRepository {
    void save(TopicEntity topicEntity);

    List<TopicEntity> findBySpeechBoardEntityIn(List<SpeechBoardEntity> speechBoardEntities);
}
