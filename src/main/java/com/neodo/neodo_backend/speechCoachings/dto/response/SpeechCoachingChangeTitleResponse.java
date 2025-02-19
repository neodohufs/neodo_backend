package com.neodo.neodo_backend.speechCoachings.dto.response;

import com.neodo.neodo_backend.speechCoachings.infrastructure.entity.SpeechCoachingEntity;

public class SpeechCoachingChangeTitleResponse {

    private Long speechCoachingId;
    private String title;

    public SpeechCoachingChangeTitleResponse(Long speechCoachingId, String title){
        this.speechCoachingId = speechCoachingId;
        this.title = title;
    }

    public static SpeechCoachingChangeTitleResponse from(SpeechCoachingEntity speechCoaching){
        return new SpeechCoachingChangeTitleResponse(speechCoaching.getSpeechCoachingId(), speechCoaching.getTitle());
    }
}
