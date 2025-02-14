package com.neodo.neodo_backend.topic.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.SpeechBoardEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speech_board_id")
    SpeechBoardEntity speechBoardEntity;

    private String topic;
}
