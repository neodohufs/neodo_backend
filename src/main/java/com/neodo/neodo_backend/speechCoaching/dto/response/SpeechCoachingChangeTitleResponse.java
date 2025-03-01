package com.neodo.neodo_backend.speechCoaching.dto.response;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import lombok.Getter;

@Getter
public class SpeechCoachingChangeTitleResponse {

    private Long speechCoachingId;
    private String title;

    public SpeechCoachingChangeTitleResponse(Long speechCoachingId, String title){
        this.speechCoachingId = speechCoachingId;
        this.title = title;
    }

    public static SpeechCoachingChangeTitleResponse from(SpeechCoachingEntity speechCoaching){
        return new SpeechCoachingChangeTitleResponse(speechCoaching.getId(), speechCoaching.getTitle());
    }
}
