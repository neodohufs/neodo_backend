package com.neodo.neodo_backend.scriptFeedback.service.port;

import com.neodo.neodo_backend.scriptFeedback.infrastructure.entity.ScriptFeedbackEntity;

import java.util.Optional;

public interface ScriptFeedbackRepository {
    Optional<ScriptFeedbackEntity> findByScriptEntity_Id(Long scriptId);

    void save(ScriptFeedbackEntity feedbackEntity);
}
