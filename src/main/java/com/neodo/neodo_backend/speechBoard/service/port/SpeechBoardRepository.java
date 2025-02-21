package com.neodo.neodo_backend.speechBoard.service.port;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;

import java.util.List;
import java.util.Optional;

public interface SpeechBoardRepository {
    SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity);

    Optional<SpeechBoardEntity> findById(Long speechBoardId);

    List<SpeechBoardEntity> findByUserId(Long userId);
}
