package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeechBoardFeedbackJpaRepository extends JpaRepository<SpeechBoardFeedbackEntity, Long> {
    Optional<SpeechBoardFeedbackEntity> findById(Long speechBoardFeedbackId);
}
