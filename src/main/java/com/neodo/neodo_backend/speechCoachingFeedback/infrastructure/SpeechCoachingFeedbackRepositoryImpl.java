package com.neodo.neodo_backend.speechCoachingFeedback.infrastructure;

import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;
import com.neodo.neodo_backend.speechCoachingFeedback.service.port.SpeechCoachingFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpeechCoachingFeedbackRepositoryImpl implements SpeechCoachingFeedbackRepository {

    private final SpeechCoachingFeedbackJpaRepository speechCoachingFeedbackJpaRepository;

    @Override
    public void save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity) {
        speechCoachingFeedbackJpaRepository.save(speechCoachingFeedbackEntity);
    }

    @Override
    public Optional<SpeechCoachingFeedbackEntity> findBySpeechCoachingEntity_Id(Long speechCoachingId){
        return speechCoachingFeedbackJpaRepository.findBySpeechCoachingEntity_Id(speechCoachingId);
    }
}
