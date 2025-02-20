package com.neodo.neodo_backend.speechboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "speech_boards")
@Getter
@NoArgsConstructor
public class SpeechBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //id 변수는 자동 증가하는 값을 가지게 됨, PK
    private Long id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String record;  // URL

    private Integer atmosphere;

    private Integer purpose;

    private Integer scale;

    private Integer audience;

    private Integer deadline;

    public SpeechBoardEntity(Long user_id, String title, LocalDateTime createdAt, String record, Integer atmosphere, Integer purpose, Integer scale, Integer audience, Integer deadline) {
        this.user_id = user_id;
        this.title = title;
        this.createdAt = createdAt;
        this.record = record;
        this.atmosphere = atmosphere;
        this.purpose = purpose;
        this.scale = scale;
        this.audience = audience;
        this.deadline = deadline;
    }
}