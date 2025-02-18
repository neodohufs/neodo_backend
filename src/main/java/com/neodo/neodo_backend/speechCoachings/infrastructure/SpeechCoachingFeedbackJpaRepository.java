package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechCoachingFeedbackJpaRepository extends JpaRepository<SpeechCoachingFeedbackEntity, Long> {
}
