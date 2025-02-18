package com.neodo.neodo_backend.speechBoards.controller;

import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/speech-boards")
public class SpeechBoardController {

    private final SpeechBoardService speechBoardService;

    @PatchMapping("{speech_board_id}/title")
    public  void speechBoardChangeTitle(@PathVariable("speech_board_id") Long speechBoardId, @Valid @RequestBody SpeechBoardChangeTitleRequest request){
        speechBoardService.speechBoardChangeTitle(speechBoardId, request);
    }
}
