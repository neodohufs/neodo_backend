package com.neodo.neodo_backend.speechCoachingFeedback.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.flask.utils.FlaskRequestUtils;
import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoaching.service.port.SpeechCoachingRepository;
import com.neodo.neodo_backend.speechCoachingFeedback.controller.port.SpeechCoachingFeedbackService;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.request.SpeechCoachingFeedbackRequest;
import com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity.SpeechCoachingFeedbackEntity;
import com.neodo.neodo_backend.speechCoachingFeedback.service.port.SpeechCoachingFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpeechCoachingFeedbackServiceImpl implements SpeechCoachingFeedbackService {

    private final SpeechCoachingRepository speechCoachingRepository;
    private final SpeechCoachingFeedbackRepository speechCoachingFeedbackRepository;
    private final FlaskRequestUtils flaskRequestUtils;

    @Override
    public SpeechCoachingFeedbackResponse getFeedback(Long speechCoachingId) {
        SpeechCoachingEntity speechCoachingEntity = speechCoachingRepository.findById(speechCoachingId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        SpeechCoachingFeedbackRequest speechCoachingFeedbackRequest = SpeechCoachingFeedbackRequest.builder()
                .record(speechCoachingEntity.getRecord())
                .build();

        SpeechCoachingFeedbackResponse speechCoachingFeedbackResponse = flaskRequestUtils.requestSpeechCoachingFeedback(speechCoachingFeedbackRequest);
        SpeechCoachingFeedbackEntity speechCoachingFeedbackEntity = SpeechCoachingFeedbackEntity.builder()
                .speechcoachingEntity(speechCoachingEntity)
                .originalStt(speechCoachingFeedbackResponse.getOriginalStt())
                .conclusion(speechCoachingFeedbackResponse.getConclusion())
                .score(speechCoachingFeedbackResponse.getScore())
                .build();
        speechCoachingFeedbackRepository.save(speechCoachingFeedbackEntity);

        return speechCoachingFeedbackResponse;
    }
}
