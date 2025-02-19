package com.neodo.neodo_backend.speechboard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import com.neodo.neodo_backend.speechboard.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/speech-boards")
public class RecordingController {
    @Autowired
    private RecordingService recordingService;

    @PostMapping("recordings")
    public ResponseEntity<CommonResponse<Object>> uploadRecording(
            @RequestParam("file") MultipartFile file,
            @RequestParam Long userId,
            @RequestParam Integer atmosphere,
            @RequestParam Integer purpose,
            @RequestParam Integer scale,
            @RequestParam Integer audience,
            @RequestParam Integer limitTime) throws IOException {

        String title = file.getOriginalFilename();

        SpeechBoardEntity recording = recordingService.saveRecording(file, userId, title, atmosphere, purpose, scale, audience, limitTime);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(recording)
                        .build());
    }

    @GetMapping("/{speechboardId}/record")
    public ResponseEntity<CommonResponse<Object>> downloadRecording(@PathVariable Long speechboardId) throws Exception {
        SpeechBoardEntity recording = recordingService.findRecordingById(speechboardId);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + recording.getFileName() + "\"")
                .body(CommonResponse.builder()
                        .response(SuccessResponseEnum.READ_S3_URL_INFO)
                        .data(recording.getFilePath())
                        .build());
    }

}