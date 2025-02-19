package com.neodo.neodo_backend.speechBoards.infrastructure;

import com.neodo.neodo_backend.common.response.responseEnum.ErrorResponseEnum;
import com.neodo.neodo_backend.exception.impl.SpeechBoardException;
import com.neodo.neodo_backend.speechBoards.dto.request.SpeechBoardChangeTitleRequest;
import com.neodo.neodo_backend.speechBoards.dto.response.SpeechBoardChangeTitleResponse;
import com.neodo.neodo_backend.speechBoards.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardRepository;
import com.neodo.neodo_backend.speechBoards.service.SpeechBoardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpeechBoardServiceImpl implements SpeechBoardService {

    private final SpeechBoardRepository speechBoardRepository;

    public SpeechBoardServiceImpl(SpeechBoardRepository speechBoardRepository) {
        this.speechBoardRepository = speechBoardRepository;
    }

    @Override
    @Transactional
    public SpeechBoardChangeTitleResponse speechBoardChangeTitle(Long speechBoardId, SpeechBoardChangeTitleRequest request){

        SpeechBoardEntity speechBoardEntity = speechBoardRepository.findById(speechBoardId)
                .orElseThrow(()-> new SpeechBoardException(ErrorResponseEnum.SPEECH_BOARD_NOT_FOUND));

        // 기존 엔티티를 기반으로 title만 변경한 새로운 엔티티 생성
        SpeechBoardEntity updatedSpeechBoardEntity = SpeechBoardEntity.builder()
                .speechBoardId(speechBoardEntity.getSpeechBoardId())
                .userId(speechBoardEntity.getUserId())
                .title(request.getTitle())                           // 새로운 title 설정
                .createdAt(speechBoardEntity.getCreatedAt())
                .record(speechBoardEntity.getRecord())
                .atmosphere(speechBoardEntity.getAtmosphere())
                .purpose(speechBoardEntity.getPurpose())
                .scale(speechBoardEntity.getScale())
                .audience(speechBoardEntity.getAudience())
                .deadline(speechBoardEntity.getDeadline())
                .build();

        // 저장소에 업데이트된 엔티티 저장
        speechBoardEntity = speechBoardRepository.save(updatedSpeechBoardEntity);

        return SpeechBoardChangeTitleResponse.from(speechBoardEntity);
    }
}
