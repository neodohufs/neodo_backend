package com.neodo.neodo_backend.speechBoard.controller;

import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.List;

public interface SpeechBoardService {
    List<SpeechBoardResponse> get(UserEntity userEntity);
}
