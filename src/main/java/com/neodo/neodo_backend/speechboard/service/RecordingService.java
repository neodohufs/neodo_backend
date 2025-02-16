package com.neodo.neodo_backend.speechboard.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.neodo.neodo_backend.speechboard.model.RecordingModel;
import com.neodo.neodo_backend.speechboard.repository.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public RecordingModel saveRecording(MultipartFile file, Long userId, String title, Integer atmosphere, Integer purpose, Integer scale, Integer audience, Integer limitTime) throws IOException {
        String fileName = UUID.randomUUID().toString() + ".m4a";
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, metadata);
        }

        RecordingModel recordingModel = new RecordingModel();
        recordingModel.setFileName(fileName);
        recordingModel.setFilePath("https://neodo-backends3bucket.s3.ap-northeast-2.amazonaws.com/" + fileName);
        recordingModel.setId(userId);
        recordingModel.setTitle(title);
        recordingModel.setUploadTime(LocalDateTime.now());
        recordingModel.getAtmosphere(atmosphere);
        recordingModel.setPurpose(purpose);
        recordingModel.setScale(scale);
        recordingModel.setAudience(audience);
        recordingModel.setLimitTime(limitTime);
        return recordingRepository.save(recordingModel);
    }

    public RecordingModel findRecordingById(Long id) throws Exception {
        return recordingRepository.findById(id).orElseThrow(() -> new Exception("Recording not found"));
    }

}