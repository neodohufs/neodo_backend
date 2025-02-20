package com.neodo.neodo_backend.speechboard.service;

import com.amazonaws.Request;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.DuplicatedResources;
import com.neodo.neodo_backend.speechboard.dto.RequestDTO;
import com.neodo.neodo_backend.speechboard.dto.ResponseDTO;
import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.neodo.neodo_backend.awsconfig.S3Config.S3_BUCKET_URL;

@Service
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("neodo-backends3bucket")
    private String bucketName;

    @Autowired
    public RecordingService(RecordingRepository recordingRepository, AmazonS3Client amazonS3Client) {
        this.recordingRepository = recordingRepository;
        this.amazonS3Client = amazonS3Client;
    }

    public ResponseDTO saveRecording(RequestDTO requestDTO, MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        }

        SpeechBoardEntity speechBoardEntity = new SpeechBoardEntity(
                requestDTO.getUserId(),
                file.getOriginalFilename(),
                LocalDateTime.now(),
                record,
                requestDTO.getAtmosphere(),
                requestDTO.getPurpose(),
                requestDTO.getScale(),
                requestDTO.getAudience(),
                requestDTO.getDeadline()
        );

        recordingRepository.save(speechBoardEntity);

        return new ResponseDTO(speechBoardEntity);
    }

    public ResponseDTO findRecordingById(Long id) {
        SpeechBoardEntity speechBoardEntity = recordingRepository.findById(id).orElseThrow(
                () -> new DuplicatedResources(ErrorResponseEnum.RECORDING_NOT_FOUND));

        return new ResponseDTO(speechBoardEntity);

    }
}
