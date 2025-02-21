package com.neodo.neodo_backend.speechBoard.service;

import com.neodo.neodo_backend.speechBoard.dto.request.RecordRequestDto;
import com.neodo.neodo_backend.speechBoard.dto.response.RecordResponseDto;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SpeechBoardService {
    RecordResponseDto saveRecording(RecordRequestDto request, MultipartFile file, UserEntity user);

    RecordResponseDto findRecording(Long id);
}
