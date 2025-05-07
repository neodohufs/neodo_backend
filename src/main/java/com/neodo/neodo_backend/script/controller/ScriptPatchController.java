package com.neodo.neodo_backend.script.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.script.controller.port.ScriptService;
import com.neodo.neodo_backend.script.dto.request.ScriptTitlePatchRequest;
import com.neodo.neodo_backend.script.dto.response.ScriptResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/scripts")
@RequiredArgsConstructor
public class ScriptPatchController {

    private final ScriptService scriptService;

    @PatchMapping("/{script_id}/title")
    public ResponseEntity<CommonResponse<ScriptResponse>> getScript(@PathVariable("script_id") Long scriptId,
                                                                    @RequestBody ScriptTitlePatchRequest scriptTitlePatchRequest) {
        ScriptResponse scriptResponse = scriptService.patchTitle(scriptId, scriptTitlePatchRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ScriptResponse>builder()
                        .response(SuccessResponseEnum.TITLE_CHANGED)
                        .data(scriptResponse)
                        .build());
    }

}
