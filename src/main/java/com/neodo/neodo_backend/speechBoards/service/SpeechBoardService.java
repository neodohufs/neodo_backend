package com.neodo.neodo_backend.speechBoards.service;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTitleResponse;

public interface SpeechBoardService {
    SpeechBoardChangeTitleResponse speechBoardChangeTitle(Long speechBoardId, SpeechBoardChangeTitleRequest request);
}
