package com.neodo.neodo_backend.script.service.port;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;

import java.util.List;
import java.util.Optional;

public interface ScriptRepository {
    void save(ScriptEntity scriptEntity);

    List<ScriptEntity> findByUserId(Long id);

    Optional<ScriptEntity> findById(Long scriptId);

    void deleteById(Long scriptId);
}
