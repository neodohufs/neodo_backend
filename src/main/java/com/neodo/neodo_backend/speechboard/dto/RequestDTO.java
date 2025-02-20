package com.neodo.neodo_backend.speechboard.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestDTO {
    private MultipartFile file;
    private Long userId;
    private Integer atmosphere;
    private Integer purpose;
    private Integer scale;
    private Integer audience;
    private Integer deadline;
}
