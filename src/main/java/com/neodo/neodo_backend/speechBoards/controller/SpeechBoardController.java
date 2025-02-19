package com.neodo.neodo_backend.speechBoards.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTitleResponse;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/speech-boards")
public class SpeechBoardController {

    private final SpeechBoardService speechBoardService;

    @PatchMapping("{speech_board_id}/title")
    public ResponseEntity<CommonResponse<SpeechBoardChangeTitleResponse>> speechBoardChangeTitle(@PathVariable("speech_board_id") Long speechBoardId, @Valid @RequestBody SpeechBoardChangeTitleRequest request){

        SpeechBoardChangeTitleResponse response = speechBoardService.speechBoardChangeTitle(speechBoardId, request);

        return ResponseEntity.ok()
                .body(CommonResponse.<SpeechBoardChangeTitleResponse>builder()
                        .response(SuccessResponseEnum.TITLE_CHANGED)
                        .data(response)
                        .build());
    }
}
