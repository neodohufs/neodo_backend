package com.neodo.neodo_backend.speechBoard.infrastructure.entity;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "speech_boards")
@Getter
public class SpeechBoardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title")
    private String title;

    @Column(name = "record")
    private String record;

    // TODO: 나머지 필드는 추후 추가
}
