package com.neodo.neodo_backend.speechboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "speech_boards")
@Getter @Setter
@AllArgsConstructor
public class SpeechBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //id 변수는 자동 증가하는 값을 가지게 됨, PK
    private Long id;

    private Long user_id;

    private String title;  // 사용자 지정 제목

    private LocalDateTime created_at;

    private String record;  // URL

    private Integer atmosphere;

    private Integer purpose;

    private Integer scale;

    private Integer audience;

    private Integer deadline;

    public SpeechBoardEntity() {

    }
}