package com.neodo.neodo_backend.speechBoard.dto.request;

import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Formality;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.Audience;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.SpeechType;
import com.neodo.neodo_backend.speechBoard.infrastructure.entity.enums.AudienceSize;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordRequestDto {
    private Formality formality;
    private SpeechType speechType;
    private AudienceSize audienceSize;
    private Audience audienceLevel;
    private Long deadline;

}
