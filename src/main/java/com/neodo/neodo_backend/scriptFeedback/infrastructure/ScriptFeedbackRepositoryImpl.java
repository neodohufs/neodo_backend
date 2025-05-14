package com.neodo.neodo_backend.scriptFeedback.infrastructure;

import com.neodo.neodo_backend.scriptFeedback.infrastructure.entity.ScriptFeedbackEntity;
import com.neodo.neodo_backend.scriptFeedback.service.port.ScriptFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScriptFeedbackRepositoryImpl implements ScriptFeedbackRepository {

    private final ScriptFeedbackJpaRepository scriptFeedbackJpaRepository;

    @Override
    public Optional<ScriptFeedbackEntity> findByScriptEntity_Id(Long scriptId) {
        return scriptFeedbackJpaRepository.findByScriptEntity_Id(scriptId);
    }

    @Override
    public void save(ScriptFeedbackEntity feedbackEntity) {
        scriptFeedbackJpaRepository.save(feedbackEntity);
    }
}
