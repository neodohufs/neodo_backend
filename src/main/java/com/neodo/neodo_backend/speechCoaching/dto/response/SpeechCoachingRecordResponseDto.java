package com.neodo.neodo_backend.speechCoaching.dto.response;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SpeechCoachingRecordResponseDto {

    private Long speechCoachingId;

    private Long topicId;

    private String topic;

    private String title;

    private LocalDateTime createdAt;

    private String record;

    public SpeechCoachingRecordResponseDto(SpeechCoachingEntity speechCoachingEntity) {
        this.speechCoachingId = speechCoachingEntity.getId();
        this.topicId = speechCoachingEntity.getTopicEntity().getId();
        this.topic = speechCoachingEntity.getTopicEntity().getTopic();
        this.title = speechCoachingEntity.getTitle();
        this.createdAt = speechCoachingEntity.getCreatedAt();
        this.record = speechCoachingEntity.getRecord();
    }

}
