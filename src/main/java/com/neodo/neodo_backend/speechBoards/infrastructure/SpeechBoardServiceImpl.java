package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechBoardException;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTitleResponse;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardService;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpeechBoardServiceImpl implements SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;

    public SpeechBoardServiceImpl(SpeechBoardRepository speechBoardRepository) {
        this.speechBoardRepository = speechBoardRepository;
    }

    @Override
    @Transactional
    public void speechBoardChangeTitle(Long speechBoardId, SpeechBoardChangeTitleRequest request){
        if(request.getSpeechBoardId() != null && !request.getSpeechBoardId().equals(speechBoardId)){
            throw new SpeechBoardException(ErrorResponseEnum.INVALID_SPEECH_BOARD_ID);
        }

        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(speechBoardId)
                .orElseThrow(()-> new SpeechBoardException(ErrorResponseEnum.SPEECH_BOARD_NOT_FOUND));

        speechBoardEntity.setTitle(request.getTitle());
    }
}
