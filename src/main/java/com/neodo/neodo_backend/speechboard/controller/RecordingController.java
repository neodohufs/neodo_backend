package com.neodo.neodo_backend.speechboard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechboard.dto.RequestDTO;
import com.neodo.neodo_backend.speechboard.dto.ResponseDTO;
import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import com.neodo.neodo_backend.speechboard.service.RecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/speech-boards")
public class RecordingController {

    private final RecordingService recordingService;

    @Autowired
    public RecordingController(RecordingService recordingService) {
        this.recordingService = recordingService;
    }

    @PostMapping("/recordings")
    public ResponseEntity<CommonResponse<Object>> uploadRecording(@RequestBody RequestDTO request) throws IOException {

        ResponseDTO responseDTO = recordingService.saveRecording(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(responseDTO)
                        .build());
    }

    @GetMapping("/{id}/record")
    public ResponseEntity<CommonResponse<ResponseDTO>> downloadRecording(@PathVariable Long id) throws Exception {
        ResponseDTO responseDTO = recordingService.findRecordingById(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + responseDTO.getTitle() + "\"")
                .body(CommonResponse.<ResponseDTO>builder()
                        .response(SuccessResponseEnum.READ_S3_URL_INFO)
                        .data(responseDTO)
                        .build());
    }
}