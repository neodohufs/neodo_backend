package com.neodo.neodo_backend.speechCoachings.controller;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTextRequest;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/speech-coachings")
public class SpeechCoachingFeedbackController {

    private final SpeechCoachingFeedbackService speechCoachingFeedbackService;

    @PatchMapping("{speech_coaching_id}/text")
    public void speechCoachingChangeText(@PathVariable("speech_coaching_id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTextRequest request){
        speechCoachingFeedbackService.speechCoachingChangeText(speechCoachingId, request);
    }
}
