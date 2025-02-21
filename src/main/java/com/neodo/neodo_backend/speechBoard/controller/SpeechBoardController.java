package com.neodo.neodo_backend.speechBoard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechBoard.dto.request.RecordRequestDto;
import com.neodo.neodo_backend.speechBoard.dto.response.RecordResponseDto;
import com.neodo.neodo_backend.speechBoard.service.SpeechBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/speech-boards")
public class SpeechBoardController {

    private final SpeechBoardService speechBoardService;

    @Autowired
    public SpeechBoardController(SpeechBoardService speechBoardService) {
        this.speechBoardService = speechBoardService;
    }

    @PostMapping("/record")
    public ResponseEntity<CommonResponse<RecordResponseDto>> uploadRecording(@RequestPart("request") RecordRequestDto request,
                                                                  @RequestPart("record") MultipartFile file,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        RecordResponseDto recordResponseDto = speechBoardService.saveRecording(request, file, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<RecordResponseDto>builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(recordResponseDto)
                        .build());
    }

    @GetMapping("/{id}/record")
    public ResponseEntity<CommonResponse<RecordResponseDto>> downloadRecording(@PathVariable Long id) {
        RecordResponseDto recordResponseDto = speechBoardService.findRecording(id);

        return ResponseEntity.ok()
                .body(CommonResponse.<RecordResponseDto>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(recordResponseDto)
                        .build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<SpeechBoardResponse>>> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SpeechBoardResponse> response = speechBoardService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<SpeechBoardResponse>>builder()
                        .response(SuccessResponseEnum.READ_SPEECH_BOARD_LIST)
                        .data(response)
                        .build());
    }
}