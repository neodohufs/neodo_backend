package com.neodo.neodo_backend.script.dto.request;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScriptCreateRequest {
    private String title;
    private String script;
    private Atmosphere atmosphere;
    private Purpose purpose;
    private Scale scale;
    private Audience audience;
    private Long deadline;
}
