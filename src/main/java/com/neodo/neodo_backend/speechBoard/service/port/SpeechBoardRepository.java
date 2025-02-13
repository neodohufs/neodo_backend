package com.neodo.neodo_backend.speechBoard.service.port;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;

import java.util.Optional;

public interface SpeechBoardRepository {

    Optional<SpeechBoardEntity> findById(Long speechBoardId);
}
