package com.neodo.neodo_backend.speechBoardFeedback.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoardFeedback.controller.port.SpeechBoardFeedbackService;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechBoardFeedbackServiceImpl implements SpeechBoardFeedbackService {

    private final SpeechBoardRepository speechBoardRepository;

    @Override
    public SpeechBoardFeedbackResponse getFeedback(Long speechBoardId) {
        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(speechBoardId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        String record = speechBoardEntity.getRecord();
        Atmosphere atmosphere = speechBoardEntity.getAtmosphere();
        Audience audience = speechBoardEntity.getAudience();
        Scale scale = speechBoardEntity.getScale();
        Purpose purpose = speechBoardEntity.getPurpose();


        return null;
    }
}
