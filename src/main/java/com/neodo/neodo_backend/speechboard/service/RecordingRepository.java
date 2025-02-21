package com.neodo.neodo_backend.speechboard.service;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordingRepository  {

    SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity);

    Optional<SpeechBoardEntity> findById(Long id);


}