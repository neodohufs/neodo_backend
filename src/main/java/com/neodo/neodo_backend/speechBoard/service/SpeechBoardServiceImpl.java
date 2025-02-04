package com.neodo.neodo_backend.speechBoard.service;

import com.neodo.neodo_backend.speechBoard.controller.SpeechBoardService;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeechBoardServiceImpl implements SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;

    @Override
    public List<SpeechBoardResponse> get(UserEntity user) {
        return speechBoardRepository.getByUserId(user.getId()).stream()
                .map(SpeechBoardResponse::from)
                .toList();
    }
}
