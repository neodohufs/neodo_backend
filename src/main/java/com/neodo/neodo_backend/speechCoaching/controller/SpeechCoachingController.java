package com.neodo.neodo_backend.speechCoaching.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingRecordRequestDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/speech-coachings")
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @Autowired
    public SpeechCoachingController(SpeechCoachingService speechCoachingService) {
        this.speechCoachingService = speechCoachingService;
    }


    @PostMapping("/record")
    public ResponseEntity<CommonResponse<SpeechCoachingRecordResponseDto>> uploadSpeechCoachingRecording(@RequestPart("request")SpeechCoachingRecordRequestDto request,
                                                                                                         @RequestPart("file")MultipartFile file,
                                                                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        SpeechCoachingRecordRequestDto speechCoachingRecordResponseDto = speechCoachingService.saveRecording(request, file, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<SpeechCoachingRecordResponseDto>builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(speechCoachingRecordResponseDto)
                        .build());
    }
}
