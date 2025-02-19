package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;

import java.util.Optional;

public interface SpeechBoardRepository {
    Optional<SpeechBoardEntity> findById(Long speechBoardId);
    SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity);
}
