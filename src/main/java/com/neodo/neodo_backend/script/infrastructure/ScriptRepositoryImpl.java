package com.neodo.neodo_backend.script.infrastructure;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import com.neodo.neodo_backend.script.service.port.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScriptRepositoryImpl implements ScriptRepository {
    private final ScriptJpaRepository scriptJpaRepository;

    @Override
    public void save(ScriptEntity scriptEntity) {
        scriptJpaRepository.save(scriptEntity);
    }

    @Override
    public List<ScriptEntity> findByUserId(Long id) {
        return scriptJpaRepository.findByUserId(id);
    }

    @Override
    public Optional<ScriptEntity> findById(Long scriptId) {
        return scriptJpaRepository.findById(scriptId);
    }
}
