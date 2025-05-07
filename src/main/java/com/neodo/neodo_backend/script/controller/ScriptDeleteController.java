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
public class ScriptDeleteController {

    private final ScriptService scriptService;

    @DeleteMapping("/{script_id}")
    public ResponseEntity<CommonResponse<ScriptResponse>> patchTitle(@PathVariable("script_id") Long scriptId) {
        ScriptResponse scriptResponse = scriptService.delete(scriptId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.<ScriptResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_DELETED)
                        .data(scriptResponse)
                        .build());
    }

}
