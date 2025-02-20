package com.neodo.neodo_backend.speechBoard.dto.request;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Atmosphere;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Purpose;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Scale;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordRequestDto {
    private Atmosphere atmosphere;
    private Purpose purpose;
    private Scale scale;
    private Audience audience;
    private Long deadline;

}
