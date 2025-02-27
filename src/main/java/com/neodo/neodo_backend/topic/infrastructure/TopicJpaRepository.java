package com.neodo.neodo_backend.topic.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicJpaRepository extends JpaRepository<TopicEntity, Long> {
    List<TopicEntity> findBySpeechBoardEntityIn(List<SpeechBoardEntity> speechBoardEntities);
}
