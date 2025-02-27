package com.neodo.neodo_backend.speechCoaching.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingRecordResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @Autowired
    public SpeechCoachingController(SpeechCoachingService speechCoachingService) {
        this.speechCoachingService = speechCoachingService;
    }


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
}