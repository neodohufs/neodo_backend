package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechBoardException;
import com.neodo.neodo_backend.exception.impl.SpeechCoachingException;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingRepository;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SpeechCoachingServiceImpl implements SpeechCoachingService {

    private final SpeechCoachingRepository speechCoachingRepository;

    public SpeechCoachingServiceImpl(SpeechCoachingRepository speechCoachingRepository) {
        this.speechCoachingRepository = speechCoachingRepository;
    }

    @Override
    @Transactional
    public void speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request){
        if(request.getSpeechCoachingId() != null && !request.getSpeechCoachingId().equals(speechCoachingId)) {
            throw new SpeechCoachingException(ErrorResponseEnum.INVALID_SPEECH_COACHING_ID);
        }

        SpeechCoachingEntity speechCoachingEntity = speechCoachingRepository.findById(request.getSpeechCoachingId())
                .orElseThrow(()-> new SpeechCoachingException(ErrorResponseEnum.SPEECH_COACHING_NOT_FOUND));
        speechCoachingEntity.setTitle(request.getTitle());
    }
}

