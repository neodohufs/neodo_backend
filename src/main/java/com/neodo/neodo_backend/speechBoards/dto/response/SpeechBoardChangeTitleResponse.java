package com.neodo.neodo_backend.speechBoards.dto.response;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechBoardChangeTitleResponse {

    private Long speechBoardId;
    private Long userId;
    private String title;

    public SpeechBoardChangeTitleResponse(Long speechBoardId, Long userId, String title) {
        this.speechBoardId = speechBoardId;
        this.userId = userId;
        this.title = title;
    }

    public static SpeechBoardChangeTitleResponse from(SpeechBoardEntity speechBoard){
        return new SpeechBoardChangeTitleResponse(speechBoard.getSpeechBoardId(), speechBoard.getUserId(), speechBoard.getTitle());
    }
}