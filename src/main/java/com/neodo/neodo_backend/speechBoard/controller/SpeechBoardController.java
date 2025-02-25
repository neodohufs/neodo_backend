package com.neodo.neodo_backend.speechBoard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechBoard.dto.request.RecordRequestDto;
import com.neodo.neodo_backend.speechBoard.dto.response.RecordResponseDto;
import com.neodo.neodo_backend.speechBoard.controller.port.SpeechBoardService;
import com.neodo.neodo_backend.speechBoard.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardChangeTitleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/speech-boards")
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
    public ResponseEntity<CommonResponse<List<SpeechBoardListResponse>>> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SpeechBoardListResponse> response = speechBoardService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<SpeechBoardListResponse>>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(response)
                        .build());
    }

    @PatchMapping("{speech_board_id}/title")
    public ResponseEntity<CommonResponse<SpeechBoardChangeTitleResponse>> speechBoardChangeTitle(@PathVariable("speech_board_id") Long speechBoardId, @Valid @RequestBody SpeechBoardChangeTitleRequest request){

        SpeechBoardChangeTitleResponse response = speechBoardService.speechBoardChangeTitle(speechBoardId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechBoardChangeTitleResponse>builder()
                        .response(SuccessResponseEnum.TITLE_CHANGED)
                        .data(response)
                        .build());
    }
}