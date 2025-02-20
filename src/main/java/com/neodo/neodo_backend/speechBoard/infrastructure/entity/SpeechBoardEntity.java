package com.neodo.neodo_backend.speechBoard.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.dto.request.RequestDTO;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "speech_boards")
@Getter
@NoArgsConstructor
public class SpeechBoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String title;

    private String record;

    @Enumerated(EnumType.STRING)
    private Atmosphere atmosphere;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Scale scale;

    @Enumerated(EnumType.STRING)
    private Audience audience;

    private Long deadline;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public SpeechBoardEntity(UserEntity userEntity, String fileName, String record, RequestDTO requestDTO) {
        this.user = userEntity;
        this.title = fileName;
        this.createdAt = LocalDateTime.now();
        this.record = record;
        this.atmosphere = requestDTO.getAtmosphere();
        this.purpose = requestDTO.getPurpose();
        this.scale = requestDTO.getScale();
        this.audience = requestDTO.getAudience();
        this.deadline = requestDTO.getDeadline();
    }

}
