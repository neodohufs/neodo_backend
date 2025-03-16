package com.neodo.neodo_backend.speechCoaching.infrastructure;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoaching.service.port.SpeechCoachingRepository;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpeechCoachingRepositoryImpl implements SpeechCoachingRepository {

    private final SpeechCoachingJpaRepository speechCoachingJpaRepository;

    @Override
    public SpeechCoachingEntity save(SpeechCoachingEntity speechCoachingEntity) {
        return speechCoachingJpaRepository.save(speechCoachingEntity);
    }

    @Override
    public Optional<SpeechCoachingEntity> findById(Long speechCoachingId) {
        return speechCoachingJpaRepository.findById(speechCoachingId);
    }

    @Override
    public List<SpeechCoachingEntity> findByTopicEntityIn(List<TopicEntity> topicEntities) {
        return speechCoachingJpaRepository.findByTopicEntityIn(topicEntities);
    }
}
