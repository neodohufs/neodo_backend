package com.neodo.neodo_backend.speechBoard.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.port.SpeechBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SpeechBoardRepositoryImpl implements SpeechBoardRepository {

    private final SpeechBoardJpaRepository speechBoardJpaRepository;

    @Override
    public Optional<SpeechBoardEntity> findById(Long speechBoardId) {
        return speechBoardJpaRepository.findById(speechBoardId);
    }

    @Override
    public SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity) {
        return speechBoardJpaRepository.save(speechBoardEntity);
    }

    @Override
    public List<SpeechBoardEntity> getByUserId(Long userId) {
        return speechBoardJpaRepository.getByUserId(userId);
    }
}
