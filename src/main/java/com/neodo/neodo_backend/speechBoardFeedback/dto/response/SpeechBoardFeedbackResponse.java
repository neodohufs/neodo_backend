package com.neodo.neodo_backend.speechBoardFeedback.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neodo.neodo_backend.speechBoardFeedback.CustomStringToListDeserializer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SpeechBoardFeedbackResponse {
    private String originalStt;
    private int score;
    private String conclusion;
    @JsonDeserialize(using = CustomStringToListDeserializer.class)
    private List<String> topics;
}
