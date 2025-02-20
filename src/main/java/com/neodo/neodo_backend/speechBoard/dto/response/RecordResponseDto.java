package com.neodo.neodo_backend.speechBoard.dto.response;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Formality;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.SpeechType;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.AudienceSize;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecordResponseDto {
    private Long id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime createdAt;

    private Formality formality;

    private SpeechType speechType;

    private AudienceSize audienceSize;

    private Audience audienceLevel;

    private Long deadline;

    public RecordResponseDto(SpeechBoardEntity speechBoardEntity) {
        this.id = speechBoardEntity.getId();
        this.user_id = speechBoardEntity.getUser().getId();
        this.title = speechBoardEntity.getTitle();
        this.createdAt = speechBoardEntity.getCreatedAt();
        this.formality = speechBoardEntity.getFormality();
        this.speechType = speechBoardEntity.getSpeechType();
        this.audienceSize = speechBoardEntity.getAudienceSize();
        this.audienceLevel = speechBoardEntity.getAudienceLevel();
        this.deadline = speechBoardEntity.getDeadline();
    }
}
