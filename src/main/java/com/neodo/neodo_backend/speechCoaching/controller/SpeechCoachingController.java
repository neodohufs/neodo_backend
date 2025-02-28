package com.neodo.neodo_backend.speechCoaching.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingChangeTitleResponse;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @PostMapping("/topics/{topic_id}/speech-coachings/record")
    public ResponseEntity<CommonResponse<SpeechCoachingRecordResponseDto>> uploadSpeechCoachingRecording(@RequestPart("record")MultipartFile file,
                                                                                                         @PathVariable("topic_id") Long topicId) {
        SpeechCoachingRecordResponseDto speechCoachingRecordResponseDto = speechCoachingService.saveRecording(file, topicId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<SpeechCoachingRecordResponseDto>builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(speechCoachingRecordResponseDto)
                        .build());
    }

    @GetMapping("/speech-coachings/{speech-coaching-id}/record")
    public ResponseEntity<CommonResponse<SpeechCoachingRecordResponseDto>> downloadRecording(@PathVariable("speech-coaching-id") Long speechCoachingId) {
        SpeechCoachingRecordResponseDto speechCoachingRecordResponseDto = speechCoachingService.findRecording(speechCoachingId);
        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingRecordResponseDto>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(speechCoachingRecordResponseDto)
                        .build());
    }

    @GetMapping("/speech-coachings")
    public ResponseEntity<CommonResponse<List<SpeechCoachingTopicResponse>>> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SpeechCoachingTopicResponse> speechCoachingTopicResponse = speechCoachingService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<SpeechCoachingTopicResponse>>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(speechCoachingTopicResponse)
                        .build());
    }

    @PatchMapping("/speech-coachings/{speech-coaching-id}/title")
    public ResponseEntity<CommonResponse<SpeechCoachingChangeTitleResponse>> speechCoachingChangeTitle(@PathVariable("speech-coaching-id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTitleRequest request){
        SpeechCoachingChangeTitleResponse response = speechCoachingService.speechCoachingChangeTitle(speechCoachingId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingChangeTitleResponse>builder()
                        .response(SuccessResponseEnum.TITLE_CHANGED)
                        .data(response)
                        .build());

    }

}
