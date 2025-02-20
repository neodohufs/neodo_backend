package com.neodo.neodo_backend.speechBoardFeedback.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.external.flask.utils.FlaskRequestUtils;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoardFeedback.controller.port.SpeechBoardFeedbackService;
import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardFeedbackRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechBoardFeedback.service.port.SpeechBoardFeedbackRepository;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.topic.service.port.TopicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class SpeechBoardFeedbackServiceImpl implements SpeechBoardFeedbackService {

    private final SpeechBoardRepository speechBoardRepository;
    private final SpeechBoardFeedbackRepository speechBoardFeedbackRepository;
    private final TopicRepository topicRepository;
    private final FlaskRequestUtils flaskRequestUtils;

    @Override
    public SpeechBoardFeedbackResponse getFeedback(Long speechBoardId) {
        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(speechBoardId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        SpeechBoardFeedbackRequest speechBoardFeedbackRequest = SpeechBoardFeedbackRequest.builder()
                .record(speechBoardEntity.getRecord())
                .formality(speechBoardEntity.getFormality())
                .audienceLevel(speechBoardEntity.getAudienceLevel())
                .scale(speechBoardEntity.getAudienceSize())
                .purpose(speechBoardEntity.getSpeechType())
                .deadline(speechBoardEntity.getDeadline())
                .build();

        SpeechBoardFeedbackResponse speechBoardFeedbackResponse = flaskRequestUtils.requestSpeechBoardFeedback(speechBoardFeedbackRequest);

        SpeechBoardFeedbackEntity speechBoardFeedbackEntity = SpeechBoardFeedbackEntity.builder()
                .speechBoardEntity(speechBoardEntity)
                .originalStt(speechBoardFeedbackResponse.getOriginalStt())
                .conclusion(speechBoardFeedbackResponse.getConclusion())
                .score(speechBoardFeedbackResponse.getScore())
                .build();
        speechBoardFeedbackRepository.save(speechBoardFeedbackEntity);

        for (String topic : speechBoardFeedbackResponse.getTopics()) {
            TopicEntity topicEntity = TopicEntity.builder()
                    .speechBoardEntity(speechBoardEntity)
                    .topic(topic)
                    .build();
            topicRepository.save(topicEntity);
        }

        return speechBoardFeedbackResponse;
    }
}
