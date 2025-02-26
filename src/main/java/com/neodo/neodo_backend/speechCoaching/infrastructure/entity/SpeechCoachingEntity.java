package com.neodo.neodo_backend.speechCoaching.infrastructure.entity;

import com.neodo.neodo_backend.topic.infrastructure.entity.TopicEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "speech_coachings")
@Getter
public class SpeechCoachingEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;

    private String record;

    private String title;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    public SpeechCoachingEntity(String originalFilename, String record) {
    }

    public SpeechCoachingEntity() {

    }

    @Builder
    public SpeechCoachingEntity(String title, String record, TopicEntity topicEntity) {
        this.title = title;
        this.record = record;
        this.topicEntity = topicEntity;
        this.createdAt = LocalDateTime.now();
    }
}
