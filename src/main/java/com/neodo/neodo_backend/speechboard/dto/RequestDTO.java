package com.neodo.neodo_backend.speechboard.dto;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class RequestDTO {
    private Long userId;
    private Integer atmosphere;
    private Integer purpose;
    private Integer scale;
    private Integer audience;
    private Integer deadline;

}
