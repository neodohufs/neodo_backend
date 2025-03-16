package com.neodo.neodo_backend.speechCoaching.infrastructure;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpeechCoachingJpaRepository extends JpaRepository<SpeechCoachingEntity, Long> {
    Optional<SpeechCoachingEntity> findById(Long speechCoachingId);
    List<SpeechCoachingEntity> findByTopicEntityIn(List<TopicEntity> topicEntities);
}
