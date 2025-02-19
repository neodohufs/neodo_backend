package com.neodo.neodo_backend.speechBoards.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTextResponse;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardFeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-boards")
public class SpeechBoardFeedbackController {

    private final SpeechBoardFeedbackService speechBoardFeedbackService;

    @PatchMapping("{speech_board_id}/text")
    public ResponseEntity<CommonResponse<SpeechBoardChangeTextResponse>> speechBoardChangeText(@PathVariable("speech_board_id") Long speechBoardId, @Valid @RequestBody SpeechBoardChangeTextRequest request){
        SpeechBoardChangeTextResponse response = speechBoardFeedbackService.speechBoardChangeText(speechBoardId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechBoardChangeTextResponse>builder()
                        .response(SuccessResponseEnum.TEXT_CHANGED)
                        .data(response)
                        .build());
    }
}
