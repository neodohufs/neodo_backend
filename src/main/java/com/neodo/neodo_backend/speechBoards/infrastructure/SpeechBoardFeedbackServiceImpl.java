package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechBoardException;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardFeedbackRepository;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeechBoardFeedbackServiceImpl implements SpeechBoardFeedbackService {

    private final SpeechBoardFeedbackRepository speechBoardFeedbackRepository;

    public SpeechBoardFeedbackServiceImpl(SpeechBoardFeedbackRepository speechBoardFeedbackRepository) {
        this.speechBoardFeedbackRepository = speechBoardFeedbackRepository;
    }

    @Override
    @Transactional
    public void speechBoardChangeText(Long speechBoardId ,SpeechBoardChangeTextRequest request){
        if(request.getSpeechBoardId() != null && !request.getSpeechBoardId().equals(speechBoardId)){
            throw new SpeechBoardException(ErrorResponseEnum.INVALID_SPEECH_BOARD_ID);
        }

        SpeechBoardFeedbackEntity speechBoardFeedbackEntity = speechBoardFeedbackRepository.findById(request.getSpeechBoardFeedbackId())
                .orElseThrow(()-> new SpeechBoardException(ErrorResponseEnum.SPEECH_BOARD_FEEDBACK_NOT_FOUND));

        speechBoardFeedbackEntity.setModifiedStt(request.getModified_stt());
    }
}
