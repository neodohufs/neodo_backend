package com.neodo.neodo_backend.scriptFeedback.infrastructure;

import com.neodo.neodo_backend.scriptFeedback.infrastructure.entity.ScriptFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScriptFeedbackJpaRepository extends JpaRepository<ScriptFeedbackEntity, Long> {
    Optional<ScriptFeedbackEntity> findByScriptEntity_Id(Long scriptId);
}
