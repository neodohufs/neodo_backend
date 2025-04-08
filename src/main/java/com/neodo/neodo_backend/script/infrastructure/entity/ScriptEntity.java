package com.neodo.neodo_backend.script.infrastructure.entity;

import com.neodo.neodo_backend.script.dto.request.ScriptCreateRequest;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name= "scripts")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ScriptEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String title;

    private String script;

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

    public static ScriptEntity of(ScriptCreateRequest request, UserEntity user) {
        return ScriptEntity.builder()
                .user(user)
                .title(request.getTitle())
                .script(request.getScript())
                .atmosphere(request.getAtmosphere())
                .purpose(request.getPurpose())
                .scale(request.getScale())
                .audience(request.getAudience())
                .deadline(request.getDeadline())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
