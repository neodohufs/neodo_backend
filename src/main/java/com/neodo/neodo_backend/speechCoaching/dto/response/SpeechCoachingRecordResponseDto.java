package com.neodo.neodo_backend.speechCoaching.dto.response;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SpeechCoachingRecordResponseDto {

    private Long id;

    private TopicEntity topic_id;

    private String title;

    private LocalDateTime createdAt;

    private String record;


    public SpeechCoachingRecordResponseDto(SpeechCoachingEntity speechCoachingEntity) {
        this.id = speechCoachingEntity.getId();
        this.topic_id = speechCoachingEntity.getTopicEntity();
        this.title = speechCoachingEntity.getTitle();
        this.createdAt = speechCoachingEntity.getCreatedAt();
        this.record = speechCoachingEntity.getRecord();
    }

}
