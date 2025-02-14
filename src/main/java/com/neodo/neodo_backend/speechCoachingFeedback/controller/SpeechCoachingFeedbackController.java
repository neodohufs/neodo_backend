package com.neodo.neodo_backend.speechCoachingFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import com.neodo.neodo_backend.speechCoachingFeedback.controller.port.SpeechCoachingFeedbackService;
import com.neodo.neodo_backend.speechCoachingFeedback.dto.reponse.SpeechCoachingFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-coachings")
public class SpeechCoachingFeedbackController {

    private final SpeechCoachingFeedbackService speechCoachingFeedbackService;

    @GetMapping("/{speech-coaching-id}/feedback")
    public ResponseEntity<CommonResponse<SpeechCoachingFeedbackResponse>> getFeedback(@PathVariable("speech-coaching-id") Long speechCoachingId) {
        SpeechCoachingFeedbackResponse speechCoachingFeedbackResponse = speechCoachingFeedbackService.getFeedback(speechCoachingId);
        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechBoardFeedbackResponse>builder()
                        .response(SuccessResponseEnum.GET_SPEECH_COACHING_FEEDBACK)
                        .data(speechCoachingFeedbackResponse)
                        .build());
    }

}
