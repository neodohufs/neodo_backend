package com.neodo.neodo_backend.speechCoaching.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechCoaching.controller.port.SpeechCoachingService;
import com.neodo.neodo_backend.speechCoaching.dto.response.SpeechCoachingTopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/speech-coachings")
@RequiredArgsConstructor
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<SpeechCoachingTopicResponse>>> get (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SpeechCoachingTopicResponse> speechCoachingTopicResponse = speechCoachingService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<SpeechCoachingTopicResponse>>builder()
                        .response(SuccessResponseEnum.READ_SPEECH_COACHING)
                        .data(speechCoachingTopicResponse)
                        .build());
    }
}
