package com.neodo.neodo_backend.speechCoachings.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "speech_coaching_feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeechCoachingFeedbackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speech_coaching_feedback_id")
    private Long speechBoardFeedbackId;

    @Column(name = "speech_coaching_id", nullable = false)
    private Long speechBoardId;

    @Column(name = "original_stt")
    private String originalStt;

    @Column(name = "modified_stt")
    private String modifiedStt;

    private String annotation;

    private Short score;

    private String conclusion;
}
