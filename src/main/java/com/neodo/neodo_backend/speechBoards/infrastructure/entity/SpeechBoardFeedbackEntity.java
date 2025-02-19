package com.neodo.neodo_backend.speechBoards.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "speech_board_feedbacks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpeechBoardFeedbackEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speech_board_feedback_id")
    private Long speechBoardFeedbackId;

    @Column(name = "speech_board_id", nullable = false)
    private Long speechBoardId;

    @Column(name = "original_stt")
    private String originalStt;

    @Column(name = "modified_stt")
    private String modifiedStt;

    private String annotation;

    private Short score;

    private String conclusion;

    public void setModifiedStt(String modifiedStt) {
        this.modifiedStt = modifiedStt;
    }
}
