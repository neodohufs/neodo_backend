package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechBoardException;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTitleResponse;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeechBoardServiceImpl implements SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;

    public SpeechBoardServiceImpl(SpeechBoardRepository speechBoardRepository) {
        this.speechBoardRepository = speechBoardRepository;
    }

    @Override
    @Transactional
    public SpeechBoardChangeTitleResponse speechBoardChangeTitle(Long speechBoardId, SpeechBoardChangeTitleRequest request){

        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(speechBoardId)
                .orElseThrow(()-> new SpeechBoardException(ErrorResponseEnum.SPEECH_BOARD_NOT_FOUND));

        speechBoardEntity.setTitle(request.getTitle()); //제목 업데이트

        speechBoardRepository.save(speechBoardEntity);

        return SpeechBoardChangeTitleResponse.from(speechBoardEntity);
    }
}
