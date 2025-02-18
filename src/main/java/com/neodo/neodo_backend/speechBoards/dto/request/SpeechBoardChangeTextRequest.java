package com.neodo.neodo_backend.speechBoards.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechBoardChangeTextRequest {

    @NotBlank(message = "스피치 보드 피드백 아이디는 필수입니다.")
    private Long speechBoardFeedbackId;

    @NotBlank(message = "스피치 보드 아이디는 필수 입니다.")
    private Long speechBoardId;

    @NotBlank(message = "변경할 내용은 필수입니다.")
    private String modified_stt;
}
