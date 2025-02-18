package com.neodo.neodo_backend.speechCoachings.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechCoachingChangeTitleRequest {

    @NotBlank(message = "스피치 코칭 아이디는 필수 입니다.")
    private Long speechCoachingId;

    @NotBlank(message = "변경할 제목은 필수입니다.")
    private String title;
}
