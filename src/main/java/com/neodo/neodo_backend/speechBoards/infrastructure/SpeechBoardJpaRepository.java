package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeechBoardJpaRepository extends JpaRepository<SpeechBoardEntity, Long> {
    Optional<SpeechBoardEntity> findById(Long speechBoardId);
}
