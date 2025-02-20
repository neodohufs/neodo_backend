package com.neodo.neodo_backend.speechboard.repository;

import com.neodo.neodo_backend.speechboard.model.SpeechBoardEntity;
import com.neodo.neodo_backend.speechboard.service.RecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RecordingRepositoryImpl implements RecordingRepository {

    private final RecordingJpaRepository recordingJpaRepository;

    @Override
    public SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity) {
        return recordingJpaRepository.save(speechBoardEntity);
    }

    @Override
    public Optional<SpeechBoardEntity> findById(Long id) {
        return recordingJpaRepository.findById(id);
    }
}
