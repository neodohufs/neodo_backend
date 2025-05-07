package com.neodo.neodo_backend.script.controller.port;

import com.neodo.neodo_backend.script.dto.request.ScriptCreateRequest;
import com.neodo.neodo_backend.script.dto.request.ScriptTextPatchRequest;
import com.neodo.neodo_backend.script.dto.request.ScriptTitlePatchRequest;
import com.neodo.neodo_backend.script.dto.response.ScriptCreateResponse;
import com.neodo.neodo_backend.script.dto.response.ScriptListResponse;
import com.neodo.neodo_backend.script.dto.response.ScriptResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.List;

public interface ScriptService {
    ScriptCreateResponse create(ScriptCreateRequest request, UserEntity user);

    List<ScriptListResponse> getScripts(UserEntity user);

    ScriptResponse get(Long scriptId);

    ScriptResponse patchTitle(Long scriptId, ScriptTitlePatchRequest scriptTitlePatchRequest);

    ScriptResponse patchText(Long scriptId, ScriptTextPatchRequest scriptTextPatchRequest);
}
