package com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "speech_coaching_feedbacks")
public class SpeechCoachingFeedbackEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speech_coaching_id")
    private SpeechCoachingEntity speechcoachingEntity;

    private String originalStt;

    private String modifiedStt;

    private int score;

    private String conclusion;

    @Builder
    public SpeechCoachingFeedbackEntity(SpeechCoachingEntity speechcoachingEntity, String originalStt, String modifiedStt, int score, String conclusion) {
        this.speechcoachingEntity = speechcoachingEntity;
        this.originalStt = originalStt;
        this.modifiedStt = modifiedStt;
        this.score = score;
        this.conclusion = conclusion;
    }
}
