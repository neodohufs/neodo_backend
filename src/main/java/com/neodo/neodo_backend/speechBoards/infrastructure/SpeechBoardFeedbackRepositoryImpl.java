package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardFeedbackRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpeechBoardFeedbackRepositoryImpl implements SpeechBoardFeedbackRepository {
    private final SpeechBoardFeedbackJpaRepository speechBoardFeedbackJpaRepository;

    public SpeechBoardFeedbackRepositoryImpl(SpeechBoardFeedbackJpaRepository speechBoardFeedbackJpaRepository) {
        this.speechBoardFeedbackJpaRepository = speechBoardFeedbackJpaRepository;
    }

    @Override
    public Optional<SpeechBoardFeedbackEntity> findById(Long speechBoardFeedbackId) {
        return speechBoardFeedbackJpaRepository.findById(speechBoardFeedbackId);
    }

    @Override
    public SpeechBoardFeedbackEntity save(SpeechBoardFeedbackEntity speechBoardFeedbackEntity){
        return speechBoardFeedbackJpaRepository.save(speechBoardFeedbackEntity);
    }
}
