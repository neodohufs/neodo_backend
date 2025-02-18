package com.neodo.neodo_backend.speechBoards.controller;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/speech-boards")
public class SpeechBoardFeedbackController {

    private final SpeechBoardFeedbackService speechBoardFeedbackService;

    @PatchMapping("{speech_board_id}/text")
    public void speechBoardChangeText(@PathVariable("speech_board_id") Long speechBoardId, @Valid @RequestBody SpeechBoardChangeTextRequest request){
        speechBoardFeedbackService.speechBoardChangeText(speechBoardId, request);
    }
}
