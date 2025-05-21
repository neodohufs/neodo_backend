package com.neodo.neodo_backend.scriptFeedback.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.scriptFeedback.controller.port.ScriptFeedbackService;
import com.neodo.neodo_backend.scriptFeedback.dto.response.ScriptFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scripts")
public class ScriptFeedbackController {

    private final ScriptFeedbackService scriptFeedbackService;

    @GetMapping("/{script-id}/feedback")
    public ResponseEntity<CommonResponse<ScriptFeedbackResponse>> getFeedback(@PathVariable("script-id") Long scriptId) {
        ScriptFeedbackResponse scriptFeedbackResponse = scriptFeedbackService.getFeedback(scriptId);

        return ResponseEntity.ok()
                .body(CommonResponse.<ScriptFeedbackResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(scriptFeedbackResponse)
                        .build());
    }

    @GetMapping("/{script-id}/edit-feedback")
    public ResponseEntity<CommonResponse<ScriptFeedbackResponse>> getEditFeedback(@PathVariable("script-id") Long scriptId) {
        ScriptFeedbackResponse scriptFeedbackResponse = scriptFeedbackService.getEditFeedback(scriptId);

        return ResponseEntity.ok()
                .body(CommonResponse.<ScriptFeedbackResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(scriptFeedbackResponse)
                        .build());
    }
}
