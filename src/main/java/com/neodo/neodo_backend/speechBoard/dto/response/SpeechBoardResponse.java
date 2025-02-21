package com.neodo.neodo_backend.speechBoard.dto.response;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SpeechBoardResponse {
    private Long id;
    private Long userId;
    private String title;
    private LocalDateTime createdAt;

    public static SpeechBoardResponse from(SpeechBoardEntity speechBoard) {
        return SpeechBoardResponse.builder()
                .id(speechBoard.getId())
                .userId(speechBoard.getUser().getId())
                .title(speechBoard.getTitle())
                .createdAt(speechBoard.getCreatedAt())
                .build();
    }
}
