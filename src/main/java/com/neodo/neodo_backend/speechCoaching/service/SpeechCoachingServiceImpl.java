package com.neodo.neodo_backend.speechCoaching.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ExternalServiceException;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingRecordRequestDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoaching.service.port.SpeechCoachingRepository;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.neodo.neodo_backend.external.aws.config.S3Config.S3_BUCKET_URL;

@Transactional
@Service
public class SpeechCoachingServiceImpl implements SpeechCoachingService {

    private final SpeechCoachingRepository speechCoachingRepository;

    private final AmazonS3Client amazonS3Client;

    @Value("neodo-backends3bucket")
    private String bucketName;

    @Autowired
    public SpeechCoachingServiceImpl(SpeechCoachingRepository speechCoachingRepository, AmazonS3Client amazonS3Client) {
        this.speechCoachingRepository = speechCoachingRepository;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public SpeechCoachingRecordResponseDto saveRecording(MultipartFile file) {
        String fileName = UUID.randomUUID() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        } catch (IOException e) {
            throw new ExternalServiceException(ErrorResponseEnum.EXTERNAL_SERVICE_ERROR);
        }

        SpeechCoachingEntity speechCoachingEntity = new SpeechCoachingEntity(
                record,
                file.getOriginalFilename()
        );

        speechCoachingRepository.save(speechCoachingEntity);

        return new SpeechCoachingRecordResponseDto(speechCoachingEntity);

    }

    @Override
    public SpeechCoachingRecordRequestDto findrecording(Long id) {
        return null;
    }
}
