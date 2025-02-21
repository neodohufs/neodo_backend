package com.neodo.neodo_backend.speechCoachingFeedback.infrastructure;

import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechCoachingFeedbackJpaRepository extends JpaRepository<SpeechCoachingFeedbackEntity, Long>{
}
