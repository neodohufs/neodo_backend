package com.neodo.neodo_backend.speechBoards.dto.response;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;

public class SpeechBoardChangeTitleResponse {

    private Long speechBoardId;
    private String title;

    public SpeechBoardChangeTitleResponse(Long speechBoardId, String title) {
        this.speechBoardId = speechBoardId;
        this.title = title;
    }

    public static SpeechBoardChangeTitleResponse from(SpeechBoardEntity speechBoard){
        return new SpeechBoardChangeTitleResponse(speechBoard.getId(), speechBoard.getTitle());
    }
}
