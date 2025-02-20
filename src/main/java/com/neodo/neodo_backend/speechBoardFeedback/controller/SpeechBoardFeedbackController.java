package com.neodo.neodo_backend.speechBoardFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechBoardFeedback.controller.port.SpeechBoardFeedbackService;
import com.neodo.neodo_backend.speechBoardFeedback.dto.response.SpeechBoardFeedbackResponse;
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
                        .response(SuccessResponseEnum.GET_SPEECH_BOARD_FEEDBACK)
                        .data(speechBoardFeedbackResponse)
                        .build());
    }
}
