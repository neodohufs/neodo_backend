package com.neodo.neodo_backend.speechCoachingFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechCoachingFeedback.controller.port.SpeechCoachingFeedbackService;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;
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

}
