package com.neodo.neodo_backend.speechBoardFeedback.infrastructure;

import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeechBoardFeedbackJpaRepository extends JpaRepository<SpeechBoardFeedbackEntity, Long> {
}
