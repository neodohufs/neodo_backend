package com.neodo.neodo_backend.speechBoardFeedback.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.external.flask.utils.FlaskRequestUtils;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoardFeedback.controller.port.SpeechBoardFeedbackService;
import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardFeedbackRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardChangeTextResponse;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity.SpeechBoardFeedbackEntity;
import com.neodo.neodo_backend.speechBoardFeedback.service.port.SpeechBoardFeedbackRepository;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.topic.service.port.TopicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        SpeechBoardEntity speechBoardEntity = getSpeechBoardEntity(speechBoardId);

        return speechBoardFeedbackRepository.findBySpeechBoardEntity_Id(speechBoardEntity.getId())
                .map(feedbackEntity -> buildFeedbackResponseFromEntity(feedbackEntity, speechBoardEntity))
                .orElseGet(() -> createAndSaveFeedback(speechBoardEntity));
    }

    private SpeechBoardEntity getSpeechBoardEntity(Long speechBoardId) {
        return speechBoardRepository.findById(speechBoardId)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));
    }

    private SpeechBoardFeedbackResponse buildFeedbackResponseFromEntity(SpeechBoardFeedbackEntity feedbackEntity, SpeechBoardEntity speechBoardEntity) {
        List<String> topics = topicRepository.findBySpeechBoardEntity(speechBoardEntity).stream()
                .map(TopicEntity::getTopic)
                .collect(Collectors.toList());

        return SpeechBoardFeedbackResponse.builder()
                .originalStt(feedbackEntity.getOriginalStt())
                .conclusion(feedbackEntity.getConclusion())
                .score(feedbackEntity.getScore())
                .topics(topics)
                .build();
    }

    private SpeechBoardFeedbackResponse createAndSaveFeedback(SpeechBoardEntity speechBoardEntity) {
        SpeechBoardFeedbackRequest request = SpeechBoardFeedbackRequest.builder()
                .record(speechBoardEntity.getRecord())
                .atmosphere(speechBoardEntity.getAtmosphere())
                .audience(speechBoardEntity.getAudience())
                .scale(speechBoardEntity.getScale())
                .purpose(speechBoardEntity.getPurpose())
                .deadline(speechBoardEntity.getDeadline())
                .build();

        SpeechBoardFeedbackResponse response = flaskRequestUtils.requestSpeechBoardFeedback(request);

        SpeechBoardFeedbackEntity feedbackEntity = SpeechBoardFeedbackEntity.builder()
                .speechBoardEntity(speechBoardEntity)
                .originalStt(response.getOriginalStt())
                .conclusion(response.getConclusion())
                .score(response.getScore())
                .build();
        speechBoardFeedbackRepository.save(feedbackEntity);

        saveTopics(response.getTopics(), speechBoardEntity);

        return response;
    }

    private void saveTopics(List<String> topics, SpeechBoardEntity speechBoardEntity) {
        topics.stream()
                .map(topic -> TopicEntity.builder()
                        .speechBoardEntity(speechBoardEntity)
                        .topic(topic)
                        .build())
                .forEach(topicRepository::save);
    }


    @Override
    public SpeechBoardChangeTextResponse speechBoardChangeText(Long speechBoardId , SpeechBoardChangeTextRequest request){

        SpeechBoardFeedbackEntity speechBoardFeedbackEntity = speechBoardFeedbackRepository.findBySpeechBoardEntity_Id(speechBoardId)
                .orElseThrow(()-> new ResourceException(ErrorResponseEnum.SPEECH_BOARD_FEEDBACK_NOT_FOUND));

        speechBoardFeedbackEntity.setModifiedStt(request.getModifiedStt());

        speechBoardFeedbackRepository.save(speechBoardFeedbackEntity);

        return SpeechBoardChangeTextResponse.from(speechBoardFeedbackEntity);
    }
}
