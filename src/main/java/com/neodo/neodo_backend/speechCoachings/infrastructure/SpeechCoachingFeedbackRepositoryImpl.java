package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingFeedbackRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpeechCoachingFeedbackRepositoryImpl implements SpeechCoachingFeedbackRepository {

    private final SpeechCoachingFeedbackJpaRepository speechCoachingFeedbackJpaRepository;

    public SpeechCoachingFeedbackRepositoryImpl(SpeechCoachingFeedbackJpaRepository speechCoachingFeedbackJpaRepository) {
        this.speechCoachingFeedbackJpaRepository = speechCoachingFeedbackJpaRepository;
    }

    @Override
    public Optional<SpeechCoachingFeedbackEntity> findById(Long speechCoachingFeedbackId) {
        return speechCoachingFeedbackJpaRepository.findById(speechCoachingFeedbackId);
    }

    @Override
    public SpeechCoachingFeedbackEntity save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity){
        return speechCoachingFeedbackJpaRepository.save(speechCoachingFeedbackEntity);
    }
}
