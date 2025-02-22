package com.neodo.neodo_backend.speechCoaching.dto.response;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpeechCoachingTopicResponse {
    private Long speechBoardId;
    private String title;
    private List<String> topics;

    public static SpeechCoachingTopicResponse from(SpeechBoardEntity speechBoard, List<TopicEntity> topics) {
        return SpeechCoachingTopicResponse.builder()
                .speechBoardId(speechBoard.getId())
                .title(speechBoard.getTitle())
                .topics(topics.stream().map(TopicEntity::getTopic).collect(Collectors.toList()))
                .build();
    }
}
