package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpeechBoardRepositoryImpl implements SpeechBoardRepository {

    private final SpeechBoardJpaRepository speechBoardJpaRepository;

    public SpeechBoardRepositoryImpl(SpeechBoardJpaRepository speechBoardJpaRepository){
        this.speechBoardJpaRepository = speechBoardJpaRepository;
    }

    @Override
    public Optional<SpeechBoardEntity> findById(Long speechBoardId) {
        return speechBoardJpaRepository.findById(speechBoardId);
    }

    @Override
    public SpeechBoardEntity save(SpeechBoardEntity speechBoardEntity){
        return speechBoardJpaRepository.save(speechBoardEntity);
    }


}
