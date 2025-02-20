package com.neodo.neodo_backend.speechBoard.infrastructure.entity;

import com.neodo.neodo_backend.speechBoard.dto.request.RecordRequestDto;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Formality;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.SpeechType;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.AudienceSize;
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
    private Formality formality;

    @Enumerated(EnumType.STRING)
    private SpeechType speechType;

    @Enumerated(EnumType.STRING)
    private AudienceSize audienceSize;

    @Enumerated(EnumType.STRING)
    private Audience audienceLevel;

    private Long TimeLimit;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Builder
    public SpeechBoardEntity(UserEntity userEntity, String fileName, String record, RecordRequestDto recordRequestDto) {
        this.user = userEntity;
        this.title = fileName;
        this.createdAt = LocalDateTime.now();
        this.record = record;
        this.formality = recordRequestDto.getFormality();
        this.speechType = recordRequestDto.getSpeechType();
        this.audienceSize = recordRequestDto.getAudienceSize();
        this.audienceLevel = recordRequestDto.getAudienceLevel();
        this.deadline = recordRequestDto.getDeadline();
    }

}
