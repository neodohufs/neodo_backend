package com.neodo.neodo_backend.speechBoard.dto.response;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseDTO {
    private Long id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime createdAt;

    private Atmosphere atmosphere;

    private Purpose purpose;

    private Scale scale;

    private Audience audience;

    private Long deadline;

    public ResponseDTO(SpeechBoardEntity speechBoardEntity) {
        this.id = speechBoardEntity.getId();
        this.user_id = speechBoardEntity.getUser().getId();
        this.title = speechBoardEntity.getTitle();
        this.createdAt = speechBoardEntity.getCreatedAt();
        this.atmosphere = speechBoardEntity.getAtmosphere();
        this.purpose = speechBoardEntity.getPurpose();
        this.scale = speechBoardEntity.getScale();
        this.audience = speechBoardEntity.getAudience();
        this.deadline = speechBoardEntity.getDeadline();
    }
}
