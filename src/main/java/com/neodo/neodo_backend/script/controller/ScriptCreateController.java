package com.neodo.neodo_backend.script.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.script.controller.port.ScriptService;
import com.neodo.neodo_backend.script.dto.request.ScriptCreateRequest;
import com.neodo.neodo_backend.script.dto.response.ScriptCreateResponse;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scripts")
@RequiredArgsConstructor
public class ScriptCreateController {

    private final ScriptService scriptService;

    @PostMapping
    public ResponseEntity<CommonResponse<ScriptCreateResponse>> create(@RequestBody ScriptCreateRequest request,
                                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ScriptCreateResponse scriptCreateResponse = scriptService.create(request, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ScriptCreateResponse>builder()
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .data(scriptCreateResponse)
                        .build());
    }
}
