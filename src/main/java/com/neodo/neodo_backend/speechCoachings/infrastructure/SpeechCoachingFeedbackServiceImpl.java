package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechCoachingException;
import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTextRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTextResponse;
import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingFeedbackEntity;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingFeedbackRepository;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingFeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeechCoachingFeedbackServiceImpl implements SpeechCoachingFeedbackService {

    private final SpeechCoachingFeedbackRepository speechCoachingFeedbackRepository;

    public SpeechCoachingFeedbackServiceImpl(SpeechCoachingFeedbackRepository speechCoachingFeedbackRepository) {
        this.speechCoachingFeedbackRepository = speechCoachingFeedbackRepository;
    }

    @Override
    @Transactional
    public SpeechCoachingChangeTextResponse speechCoachingChangeText(Long speechCoachingId, SpeechCoachingChangeTextRequest request){

        SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity = speechCoachingFeedbackRepository.findById(request.getSpeechCoachingFeedbackId())
                .orElseThrow(()-> new SpeechCoachingException(ErrorResponseEnum.SPEECH_COACHING_FEEDBACK_NOT_FOUND));

        speechCoachingFeedbackEntity.setModifiedStt(request.getModified_stt());

        speechCoachingFeedbackRepository.save(speechCoachingFeedbackEntity);

        return SpeechCoachingChangeTextResponse.from(speechCoachingFeedbackEntity);
    }
}

