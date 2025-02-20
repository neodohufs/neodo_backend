package com.neodo.neodo_backend.speechBoard.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.speechBoard.dto.request.RequestDTO;
import com.neodo.neodo_backend.speechBoard.dto.response.ResponseDTO;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.neodo.neodo_backend.awsconfig.S3Config.S3_BUCKET_URL;

@Service
public class SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("neodo-backends3bucket")
    private String bucketName;

    @Autowired
    public SpeechBoardService(SpeechBoardRepository speechBoardRepository, AmazonS3Client amazonS3Client) {
        this.speechBoardRepository = speechBoardRepository;
        this.amazonS3Client = amazonS3Client;
    }

    public ResponseDTO saveRecording(RequestDTO requestDTO, MultipartFile file, UserEntity userEntity) throws IOException {
        String fileName = UUID.randomUUID() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        }

        SpeechBoardEntity speechBoardEntity = new SpeechBoardEntity(
                userEntity,
                file.getOriginalFilename(),
                record,
                requestDTO
        );

        speechBoardRepository.save(speechBoardEntity);

        return new ResponseDTO(speechBoardEntity);
    }

    public ResponseDTO findRecordingById(Long id) {
        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(id).orElseThrow(
                () -> new ResourceException(ErrorResponseEnum.RECORDING_NOT_FOUND));

        return new ResponseDTO(speechBoardEntity);

    }
}
