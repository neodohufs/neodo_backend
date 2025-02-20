package com.neodo.neodo_backend.speechBoard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechBoard.dto.request.RequestDTO;
import com.neodo.neodo_backend.speechBoard.dto.response.ResponseDTO;
import com.neodo.neodo_backend.speechBoard.service.SpeechBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/speech-boards")
public class SpeechBoardController {

    private final SpeechBoardService speechBoardService;

    @Autowired
    public SpeechBoardController(SpeechBoardService speechBoardService) {
        this.speechBoardService = speechBoardService;
    }

    @PostMapping("/recordings")
    public ResponseEntity<CommonResponse<Object>> uploadRecording(@RequestPart("request") RequestDTO request,
                                                                  @RequestPart("record") MultipartFile file,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        ResponseDTO responseDTO = speechBoardService.saveRecording(request, file, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(responseDTO)
                        .build());
    }

    @GetMapping("/{id}/record")
    public ResponseEntity<CommonResponse<ResponseDTO>> downloadRecording(@PathVariable Long id) {
        ResponseDTO responseDTO = speechBoardService.findRecordingById(id);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + responseDTO.getTitle() + "\"")
                .body(CommonResponse.<ResponseDTO>builder()
                        .response(SuccessResponseEnum.READ_S3_URL_INFO)
                        .data(responseDTO)
                        .build());
    }
}