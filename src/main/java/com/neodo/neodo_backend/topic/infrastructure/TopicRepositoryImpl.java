package com.neodo.neodo_backend.topic.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.topic.service.port.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TopicRepositoryImpl implements TopicRepository {

    private final TopicJpaRepository topicJpaRepository;

    @Override
    public void save(TopicEntity topicEntity) {
        topicJpaRepository.save(topicEntity);
    }

    @Override
    public List<TopicEntity> findBySpeechBoardEntityIn(List<SpeechBoardEntity> speechBoardEntities) {
        return topicJpaRepository.findBySpeechBoardEntityIn(speechBoardEntities);
    }
}
