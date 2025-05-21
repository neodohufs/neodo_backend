package com.neodo.neodo_backend.script.dto.response;

import com.neodo.neodo_backend.script.infrastructure.entity.ScriptEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScriptResponse {
    Long id;
    String title;
    String script;
    String editedScript;

    public static ScriptResponse from(ScriptEntity scriptEntity) {
        return ScriptResponse.builder()
                .id(scriptEntity.getId())
                .title(scriptEntity.getTitle())
                .script(scriptEntity.getScript())
                .editedScript(scriptEntity.getEditedScript())
                .build();
    }
}
