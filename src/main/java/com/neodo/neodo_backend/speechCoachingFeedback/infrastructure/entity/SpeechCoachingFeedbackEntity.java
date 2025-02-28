package com.neodo.neodo_backend.speechCoachingFeedback.infrastructure.entity;

import com.neodo.neodo_backend.speechCoaching.infrastructure.entity.SpeechCoachingEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "speech_coaching_feedbacks")
@NoArgsConstructor
public class SpeechCoachingFeedbackEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speech_coaching_id")
    private SpeechCoachingEntity speechcoachingEntity;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String originalStt;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String modifiedStt;

    private int score;

    @Lob
    @Column(columnDefinition = "TEXT")
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
