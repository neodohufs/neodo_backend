package com.neodo.neodo_backend.speechBoard.service;


import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;

import java.util.List;

public interface SpeechBoardRepository {
    List<SpeechBoardEntity> getByUserId(Long userId);
}
