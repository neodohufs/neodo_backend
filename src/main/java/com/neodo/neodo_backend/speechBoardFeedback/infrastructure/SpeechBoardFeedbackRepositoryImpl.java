package com.neodo.neodo_backend.speechBoardFeedback.infrastructure;

import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechBoardFeedback.service.port.SpeechBoardFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SpeechBoardFeedbackRepositoryImpl implements SpeechBoardFeedbackRepository {

    private final SpeechBoardFeedbackJpaRepository speechBoardFeedbackJpaRepository;

    @Override
    public void save(SpeechBoardFeedbackEntity speechBoardFeedbackEntity) {
        speechBoardFeedbackJpaRepository.save(speechBoardFeedbackEntity);
    }
}
