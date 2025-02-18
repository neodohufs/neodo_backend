package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpeechCoachingRepositoryImpl implements SpeechCoachingRepository {

    private final SpeechCoachingJpaRepository speechCoachingJpaRepository;

    public SpeechCoachingRepositoryImpl(SpeechCoachingJpaRepository speechCoachingJpaRepository){
        this.speechCoachingJpaRepository = speechCoachingJpaRepository;
    }

    @Override
    public Optional<SpeechCoachingEntity> findById(Long id) {
        return speechCoachingJpaRepository.findById(id);
    }

}
