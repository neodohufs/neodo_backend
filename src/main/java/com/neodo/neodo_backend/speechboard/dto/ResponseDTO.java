package com.neodo.neodo_backend.speechboard.dto;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDTO {
    private Long id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime createdAt;

    private Integer atmosphere;

    private Integer purpose;

    private Integer scale;

    private Integer audience;

    private Integer deadline;

    public ResponseDTO(SpeechBoardEntity speechBoardEntity) {
        this.id = speechBoardEntity.getId();
        this.user_id = speechBoardEntity.getUser_id();
        this.title = speechBoardEntity.getTitle();
        this.createdAt = speechBoardEntity.getCreatedAt();
        this.atmosphere = speechBoardEntity.getAtmosphere();
        this.purpose = speechBoardEntity.getPurpose();
        this.scale = speechBoardEntity.getScale();
        this.audience = speechBoardEntity.getAudience();
        this.deadline = speechBoardEntity.getDeadline();
    }
}
