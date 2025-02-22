package com.neodo.neodo_backend.topic.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topics")
@NoArgsConstructor
public class TopicEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speech_board_id")
    SpeechBoardEntity speechBoardEntity;

    private String topic;

    @Builder
    public TopicEntity(SpeechBoardEntity speechBoardEntity, String topic) {
        this.speechBoardEntity = speechBoardEntity;
        this.topic = topic;
    }
}
