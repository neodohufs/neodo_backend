package com.neodo.neodo_backend.speechCoaching.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class SpeechCoachingResponse {
    private Long speechCoachingId;
    private String title;
    private LocalDateTime createdAt;
    private List<Topic> topics;


    public static SpeechCoachingResponse from(SpeechCoachingEntity speechCoaching, List<Topic> topics) {
        return SpeechCoachingResponse.builder()
                .speechCoachingId(speechCoaching.getId()
                        .title(speechCoaching.getTitle()))
                .createdAt(speechCoaching.getCreatedAt())
                .topics(topics);
    }
}
