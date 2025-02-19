package com.neodo.neodo_backend.speechCoachings.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTitleResponse;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-coachings")
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @PatchMapping("{speech_coaching_id}/title")
    public ResponseEntity<CommonResponse<SpeechCoachingChangeTitleResponse>> speechCoachingChangeTitle(@PathVariable("speech_coaching_id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTitleRequest request){
        SpeechCoachingChangeTitleResponse response = speechCoachingService.speechCoachingChangeTitle(speechCoachingId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingChangeTitleResponse>builder()
                        .response(SuccessResponseEnum.TITLE_CHANGED)
                        .data(response)
                        .build());

    }
}
