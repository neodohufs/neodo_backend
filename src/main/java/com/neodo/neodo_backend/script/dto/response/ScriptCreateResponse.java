package com.neodo.neodo_backend.script.dto.response;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ScriptCreateResponse {
    Long scriptEntityId;
    Long userEntityId;
    String title;
    String script;
    Atmosphere atmosphere;
    Purpose purpose;
    Scale scale;
    Audience audience;
    Long deadline;


    public static ScriptCreateResponse from(ScriptEntity scriptEntity) {
        return ScriptCreateResponse.builder()
                .scriptEntityId(scriptEntity.getId())
                .userEntityId(scriptEntity.getUser().getId())
                .title(scriptEntity.getTitle())
                .script(scriptEntity.getScript())
                .atmosphere(scriptEntity.getAtmosphere())
                .purpose(scriptEntity.getPurpose())
                .scale(scriptEntity.getScale())
                .audience(scriptEntity.getAudience())
                .deadline(scriptEntity.getDeadline())
                .build();
    }
}
