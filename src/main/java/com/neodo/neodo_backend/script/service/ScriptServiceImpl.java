package com.neodo.neodo_backend.script.service;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.ResourceException;
import com.neodo.neodo_backend.script.controller.port.ScriptService;
import com.neodo.neodo_backend.script.dto.request.ScriptCreateRequest;
import com.neodo.neodo_backend.script.dto.request.ScriptTitlePatchRequest;
import com.neodo.neodo_backend.script.dto.response.ScriptCreateResponse;
import com.neodo.neodo_backend.script.dto.response.ScriptListResponse;
import com.neodo.neodo_backend.script.dto.response.ScriptResponse;
import com.neodo.neodo_backend.script.service.port.ScriptRepository;
import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Override
    public ScriptCreateResponse create(ScriptCreateRequest request, UserEntity user) {
        ScriptEntity scriptEntity = ScriptEntity.of(request, user);
        scriptRepository.save(scriptEntity);
        return ScriptCreateResponse.from(scriptEntity);
    }

    @Override
    public List<ScriptListResponse> getScripts(UserEntity user) {
        return scriptRepository.findByUserId(user.getId()).stream()
                .map(ScriptListResponse::from)
                .toList();

    }

    @Override
    public ScriptResponse get(Long scriptId) {
        // TODO: 피드백 정보도 가져와야 함
        return scriptRepository.findById(scriptId)
                .map(ScriptResponse::from)
                .orElseThrow(() -> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));
    }

    @Override
    public ScriptResponse patchTitle(Long scriptId, ScriptTitlePatchRequest scriptTitlePatchRequest) {
        ScriptEntity scriptEntity = scriptRepository.findById(scriptId)
                .orElseThrow(()-> new ResourceException(ErrorResponseEnum.RESOURCE_NOT_FOUND));

        scriptEntity.setTitle(scriptTitlePatchRequest.getTitle());

        return ScriptResponse.from(scriptEntity);
    }

}
