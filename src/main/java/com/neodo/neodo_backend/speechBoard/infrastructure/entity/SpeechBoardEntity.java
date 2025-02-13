package com.neodo.neodo_backend.speechBoard.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "speech_boards")
@Getter
public class SpeechBoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String record;

    @Enumerated(EnumType.STRING)
    private Atmosphere atmosphere;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Scale scale;

    @Enumerated(EnumType.STRING)
    private Audience audience;

    private int deadline;
}
