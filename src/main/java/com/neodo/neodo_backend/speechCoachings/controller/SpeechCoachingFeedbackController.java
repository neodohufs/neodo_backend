package com.neodo.neodo_backend.speechCoachings.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTextRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTextResponse;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-coachings")
public class SpeechCoachingFeedbackController {

    private final SpeechCoachingFeedbackService speechCoachingFeedbackService;

    @PatchMapping("{speech_coaching_id}/text")
    public ResponseEntity<CommonResponse<SpeechCoachingChangeTextResponse>> speechCoachingChangeText(@PathVariable("speech_coaching_id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTextRequest request){
        SpeechCoachingChangeTextResponse response = speechCoachingFeedbackService.speechCoachingChangeText(speechCoachingId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingChangeTextResponse>builder()
                        .response(SuccessResponseEnum.TEXT_CHANGED)
                        .data(response)
                        .build());
    }
}
