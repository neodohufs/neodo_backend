package com.neodo.neodo_backend.speechCoachings.controller;

import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/speech-coachings")
public class SpeechCoachingController {

    private final SpeechCoachingService speechCoachingService;

    @PatchMapping("{speech_coaching_id}/title")
    public void speechCoachingChangeTitle(@PathVariable("speech_coaching_id") Long speechCoachingId, @Valid @RequestBody SpeechCoachingChangeTitleRequest request){
        speechCoachingService.speechCoachingChangeTitle(speechCoachingId, request);
    }
}
