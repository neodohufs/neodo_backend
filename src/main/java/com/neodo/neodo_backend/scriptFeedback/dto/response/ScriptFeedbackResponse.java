package com.neodo.neodo_backend.scriptFeedback.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScriptFeedbackResponse {
    private String feedback;
}
