package com.neodo.neodo_backend.speechBoards.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechBoardChangeTitleRequest {

    @NotBlank(message = "스피치 보드 아이디는 필수 입니다.")
    private Long speechBoardId;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;
}
