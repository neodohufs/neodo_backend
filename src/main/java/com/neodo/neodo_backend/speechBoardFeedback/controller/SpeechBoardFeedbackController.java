package com.neodo.neodo_backend.speechBoardFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechBoardFeedback.controller.port.SpeechBoardFeedbackService;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
import com.neodo.neodo_backend.speechBoardFeedback.dto.request.SpeechBoardChangeTextRequest;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardChangeTextResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-boards")
public class SpeechBoardFeedbackController {

    private final SpeechBoardFeedbackService speechBoardFeedbackService;

    @PostMapping("/{speech-board-id}/feedback")
    public ResponseEntity<CommonResponse<SpeechBoardFeedbackResponse>> getFeedback(@PathVariable("speech-board-id") Long speechBoardId) {
        SpeechBoardFeedbackResponse speechBoardFeedbackResponse = speechBoardFeedbackService.getFeedback(speechBoardId);
        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechBoardFeedbackResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(speechBoardFeedbackResponse)
                        .build());
    }

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
