package com.neodo.neodo_backend.script.infrastructure;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptJpaRepository extends JpaRepository<ScriptEntity, Long> {
    List<ScriptEntity> findByUserId(Long id);
}
