package com.neodo.neodo_backend.speechCoachings.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechCoachingException;
import com.neodo.neodo_backend.speechCoachings.dto.request.SpeechCoachingChangeTitleRequest;
import com.neodo.neodo_backend.speechCoachings.dto.response.SpeechCoachingChangeTitleResponse;
import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingRepository;
import com.neodo.neodo_backend.speechCoachings.service.SpeechCoachingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeechCoachingServiceImpl implements SpeechCoachingService {

    private final SpeechCoachingRepository speechCoachingRepository;

    public SpeechCoachingServiceImpl(SpeechCoachingRepository speechCoachingRepository) {
        this.speechCoachingRepository = speechCoachingRepository;
    }

    @Override
    @Transactional
    public SpeechCoachingChangeTitleResponse speechCoachingChangeTitle(Long speechCoachingId, SpeechCoachingChangeTitleRequest request){

        SpeechCoachingEntity speechCoachingEntity = speechCoachingRepository.findById(speechCoachingId)
                .orElseThrow(()-> new SpeechCoachingException(ErrorResponseEnum.SPEECH_COACHING_NOT_FOUND));

        speechCoachingEntity.setTitle(request.getTitle()); //제목 업데이트

        speechCoachingRepository.save(speechCoachingEntity);

        return SpeechCoachingChangeTitleResponse.from(speechCoachingEntity);
    }
}

