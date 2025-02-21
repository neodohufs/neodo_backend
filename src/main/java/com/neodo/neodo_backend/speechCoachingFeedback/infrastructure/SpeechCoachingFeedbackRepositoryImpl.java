package com.neodo.neodo_backend.speechCoachingFeedback.infrastructure;

import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;
import com.neodo.neodo_backend.speechCoachingFeedback.service.port.SpeechCoachingFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpeechCoachingFeedbackRepositoryImpl implements SpeechCoachingFeedbackRepository {

    private final SpeechCoachingFeedbackJpaRepository speechCoachingFeedbackJpaRepository;

    @Override
    public void save(SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity) {
        speechCoachingFeedbackJpaRepository.save(speechCoachingFeedbackEntity);
    }
}
