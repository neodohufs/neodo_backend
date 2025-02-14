package com.neodo.neodo_backend.speechBoard.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.speechBoard.dto.response.SpeechBoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
@RequiredArgsConstructor
@RequestMapping("api/speech-boards")
public class SpeechBoardController {

    private final SpeechBoardService speechBoardService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<SpeechBoardResponse>>> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<SpeechBoardResponse> response = speechBoardService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<SpeechBoardResponse>>builder()
                        .response(SuccessResponseEnum.READ_SPEECH_BOARD_LIST)
                        .data(response)
                        .build());
    }
}
