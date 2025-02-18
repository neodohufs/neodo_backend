package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;

public interface SpeechBoardService {
    void speechBoardChangeTitle(Long speechBoardId,SpeechBoardChangeTitleRequest request);
}
