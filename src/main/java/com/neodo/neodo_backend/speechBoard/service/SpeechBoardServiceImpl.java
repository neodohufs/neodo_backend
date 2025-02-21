package com.neodo.neodo_backend.speechBoard.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ExternalServiceException;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.speechBoard.controller.port.SpeechBoardService;
import com.neodo.neodo_backend.speechBoard.dto.request.RecordRequestDto;
import com.neodo.neodo_backend.speechBoard.dto.response.RecordResponseDto;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardResponse;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import static com.neodo.neodo_backend.external.aws.config.S3Config.S3_BUCKET_URL;

@Transactional
@Service
public class SpeechBoardServiceImpl implements SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("neodo-backends3bucket")
    private String bucketName;

    @Autowired
    public SpeechBoardServiceImpl(SpeechBoardRepository speechBoardRepository, AmazonS3Client amazonS3Client) {
        this.speechBoardRepository = speechBoardRepository;
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public RecordResponseDto saveRecording(RecordRequestDto recordRequestDto, MultipartFile file, UserEntity userEntity) {
        String fileName = UUID.randomUUID() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        } catch (IOException e) {
            throw new ExternalServiceException(ErrorResponseEnum.EXTERNAL_SERVICE_ERROR);
        }

        SpeechBoardEntity speechBoardEntity = new SpeechBoardEntity(
                userEntity,
                file.getOriginalFilename(),
                record,
                recordRequestDto
        );

        speechBoardRepository.save(speechBoardEntity);

        return new RecordResponseDto(speechBoardEntity);
    }

    @Override
    public RecordResponseDto findRecording(Long id) {
        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(id).orElseThrow(
                () -> new ResourceException(ErrorResponseEnum.RECORDING_NOT_FOUND));

        return new RecordResponseDto(speechBoardEntity);

    }

    @Override
    public List<SpeechBoardResponse> get(UserEntity user) {
        return speechBoardRepository.getByUserId(user.getId()).stream()
                .map(SpeechBoardResponse::from)
                .toList();
    }
}
