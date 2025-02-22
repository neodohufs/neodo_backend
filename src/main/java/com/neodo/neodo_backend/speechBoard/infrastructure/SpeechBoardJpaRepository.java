package com.neodo.neodo_backend.speechBoard.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SpeechBoardJpaRepository extends JpaRepository<SpeechBoardEntity, Long> {

    List<SpeechBoardEntity> findByUserId(Long userId);
}
