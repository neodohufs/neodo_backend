package com.neodo.neodo_backend.speechBoardFeedback.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "speech_board_feedbacks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpeechBoardFeedbackEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speech_board_id")
    private SpeechBoardEntity speechBoardEntity;

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

    public void setModifiedStt(String modifiedStt) {
        this.modifiedStt = modifiedStt;
    }

    @Builder
    public SpeechBoardFeedbackEntity(SpeechBoardEntity speechBoardEntity, String originalStt, String modifiedStt, int score, String conclusion) {
        this.speechBoardEntity = speechBoardEntity;
        this.originalStt = originalStt;
        this.modifiedStt = modifiedStt;
        this.score = score;
        this.conclusion = conclusion;
    }
}
