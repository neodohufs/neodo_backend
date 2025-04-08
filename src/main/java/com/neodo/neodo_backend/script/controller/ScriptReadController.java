package com.neodo.neodo_backend.script.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.script.controller.port.ScriptService;
import com.neodo.neodo_backend.script.dto.response.ScriptListResponse;
import com.neodo.neodo_backend.script.dto.response.ScriptResponse;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scripts")
@RequiredArgsConstructor
public class ScriptReadController {

    private final ScriptService scriptService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<ScriptListResponse>>> getScripts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ScriptListResponse> scriptListResponse = scriptService.getScripts(userDetails.getUser());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<List<ScriptListResponse>>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(scriptListResponse)
                        .build());
    }

    @GetMapping("/{script_id}")
    public ResponseEntity<CommonResponse<ScriptResponse>> getScript(@PathVariable("script_id") Long scriptId) {
        ScriptResponse scriptResponse = scriptService.get(scriptId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ScriptResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_GET)
                        .data(scriptResponse)
                        .build());
    }
}
