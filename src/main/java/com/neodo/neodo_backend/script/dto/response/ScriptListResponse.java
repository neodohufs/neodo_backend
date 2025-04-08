package com.neodo.neodo_backend.script.dto.response;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ScriptListResponse {
    Long id;
    String title;
    LocalDateTime createdAt;

    public static ScriptListResponse from(ScriptEntity scriptEntity) {
        return ScriptListResponse.builder()
                .id(scriptEntity.getId())
                .title(scriptEntity.getTitle())
                .createdAt(scriptEntity.getCreatedAt())
                .build();
    }
}
