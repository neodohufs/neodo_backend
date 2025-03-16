package com.neodo.neodo_backend.speechCoaching.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ExternalServiceException;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingChangeTitleResponse;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoaching.service.port.SpeechCoachingRepository;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.topic.service.port.TopicRepository;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.neodo.neodo_backend.external.aws.config.S3Config.S3_BUCKET_URL;

@Transactional
@Service
@RequiredArgsConstructor
public class SpeechCoachingServiceImpl implements SpeechCoachingService {
    private final SpeechBoardRepository speechBoardRepository;

    private final SpeechCoachingRepository speechCoachingRepository;

    private final AmazonS3Client amazonS3Client;

    private final TopicRepository topicRepository;

    @Value("neodo-backends3bucket")
    private String bucketName;

    @Override
    public SpeechCoachingRecordResponseDto saveRecording(MultipartFile file,Long topicId) {
        TopicEntity topicEntity = topicRepository.findById(topicId).orElseThrow(
                () -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        String fileName = UUID.randomUUID() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        } catch (IOException e) {
            throw new ExternalServiceException(ErrorResponseEnum.EXTERNAL_SERVICE_ERROR);
        }

        SpeechCoachingEntity speechCoachingEntity = SpeechCoachingEntity.builder()
                .title(fileName)
                .record(record)
                .topicEntity(topicEntity)
                .build();

        speechCoachingRepository.save(speechCoachingEntity);

        return new SpeechCoachingRecordResponseDto(speechCoachingEntity);

    }

    @Override
    public SpeechCoachingRecordResponseDto findRecording(Long speechCoachingId) {
        SpeechCoachingEntity speechCoachingEntity = speechCoachingRepository.findById(speechCoachingId).orElseThrow(
                () -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        return new SpeechCoachingRecordResponseDto(speechCoachingEntity);
    }

    @Override
    public List<SpeechCoachingTopicResponse> get(UserEntity user) {
        List<SpeechBoardEntity> speechBoardEntities = speechBoardRepository.findByUserId(user.getId());
        List<TopicEntity> topicEntities = topicRepository.findBySpeechBoardEntityIn(speechBoardEntities);
        List<SpeechCoachingEntity> speechCoachingEntities = speechCoachingRepository.findByTopicEntityIn(topicEntities);

        Map<Long, SpeechCoachingEntity> speechCoachingEntityByTopicId = speechCoachingEntities.stream()
                .collect(Collectors.toMap(
                        speechCoachingEntity -> speechCoachingEntity.getTopicEntity().getId(),
                        Function.identity()));

        Map<Long, List<TopicEntity>> topicsBySpeechBoardId = topicEntities.stream()
                .collect(Collectors.groupingBy(topic -> topic.getSpeechBoardEntity().getId()));

        return speechBoardEntities.stream()
                .map(speechBoardEntity -> SpeechCoachingTopicResponse.from(
                        speechBoardEntity,
                        topicsBySpeechBoardId.getOrDefault(speechBoardEntity.getId(), Collections.emptyList()),
                        speechCoachingEntityByTopicId))
                .collect(Collectors.toList());
    }

    @Override
    public SpeechCoachingChangeTitleResponse speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request){

        SpeechCoachingEntity speechCoachingEntity = speechCoachingRepository.findById(speechCoachingId)
                .orElseThrow(()-> new ResourceException(ErrorResponseEnum.SPEECH_COACHING_NOT_FOUND));

        speechCoachingEntity.setTitle(request.getTitle()); //제목 업데이트

        speechCoachingRepository.save(speechCoachingEntity);

        return SpeechCoachingChangeTitleResponse.from(speechCoachingEntity);
    }
}
