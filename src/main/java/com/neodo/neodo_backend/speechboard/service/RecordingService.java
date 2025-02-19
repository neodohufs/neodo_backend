package com.neodo.neodo_backend.speechboard.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import com.neodo.neodo_backend.speechboard.repository.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.neodo.neodo_backend.awsconfig.S3Config.S3_BUCKET_URL;

@Service  //이 클래스는 Service 클래스
public class RecordingService {
    @Autowired
    private RecordingRepository recordingRepository;

    @Autowired
    private final AmazonS3Client amazonS3Client;

    @Value("neodo-backends3bucket")
    private String bucketName;

    public RecordingService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public SpeechBoardEntity saveRecording(MultipartFile file, Long userId, String title, Integer atmosphere, Integer purpose, Integer scale, Integer audience, Integer deadline) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".m4a";
        String record = S3_BUCKET_URL + fileName;  // URL 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        }

        SpeechBoardEntity speechBoardEntity = new SpeechBoardEntity(
                        null,
                userId,
                title,
                LocalDateTime.now(),
                record,
                atmosphere,
                purpose,
                scale,
                audience,
                deadline
                );
        return recordingRepository.save(speechBoardEntity);
    }

    public SpeechBoardEntity findRecordingById(Long id) throws Exception {
        return recordingRepository.findById(id).orElseThrow(() -> new Exception("Recording not found"));
    }

}