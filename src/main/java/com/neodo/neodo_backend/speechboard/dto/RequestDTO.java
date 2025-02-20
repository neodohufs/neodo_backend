package com.neodo.neodo_backend.speechboard.dto;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestDTO {
    private Long userId;
    private Integer atmosphere;
    private Integer purpose;
    private Integer scale;
    private Integer audience;
    private Integer deadline;

    public RequestDTO(SpeechBoardEntity speechBoardEntity) {
        this.userId = speechBoardEntity.getUser_id();
        this.atmosphere = speechBoardEntity.getAtmosphere();
        this.purpose = speechBoardEntity.getPurpose();
        this.scale = speechBoardEntity.getScale();
        this.audience = speechBoardEntity.getAudience();
        this.deadline = speechBoardEntity.getDeadline();
    }
}
