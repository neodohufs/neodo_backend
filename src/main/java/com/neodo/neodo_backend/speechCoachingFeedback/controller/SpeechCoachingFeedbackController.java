package com.neodo.neodo_backend.speechCoachingFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechCoachingFeedback.controller.port.SpeechCoachingFeedbackService;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.request.SpeechCoachingChangeTextRequest;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingChangeTextResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-coachings")
public class SpeechCoachingFeedbackController {

    private final SpeechCoachingFeedbackService speechCoachingFeedbackService;

    @PostMapping("/{speech-coaching-id}/feedback")
    public ResponseEntity<CommonResponse<SpeechCoachingFeedbackResponse>> getFeedback(@PathVariable("speech-coaching-id") Long speechCoachingId) {
        SpeechCoachingFeedbackResponse speechCoachingFeedbackResponse = speechCoachingFeedbackService.getFeedback(speechCoachingId);
        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingFeedbackResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(speechCoachingFeedbackResponse)
                        .build());
    }

    @PatchMapping("{speech-coaching-id}/text")
    public ResponseEntity<CommonResponse<SpeechCoachingChangeTextResponse>> speechCoachingChangeText(@PathVariable("speech-coaching-id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTextRequest request){
        SpeechCoachingChangeTextResponse response = speechCoachingFeedbackService.speechCoachingChangeText(speechCoachingId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechCoachingChangeTextResponse>builder()
                        .response(SuccessResponseEnum.TEXT_CHANGED)
                        .data(response)
                        .build());
    }

}
