package com.neodo.neodo_backend.speechboard.repository;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingJpaRepository extends JpaRepository<SpeechBoardEntity, Long> {
}
