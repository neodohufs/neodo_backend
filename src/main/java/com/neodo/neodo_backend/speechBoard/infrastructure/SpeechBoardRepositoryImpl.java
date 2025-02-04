package com.neodo.neodo_backend.speechBoard.infrastructure;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoard.service.SpeechBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpeechBoardRepositoryImpl implements SpeechBoardRepository {

    private final SpeechBoardJpaRepository speechBoardJpaRepository;

    @Override
    public List<SpeechBoardEntity> getByUserId(Long userId) {
        return speechBoardJpaRepository.getByUserId(userId);
    }
}
