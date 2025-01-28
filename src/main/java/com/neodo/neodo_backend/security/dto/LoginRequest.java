package com.neodo.neodo_backend.security.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String email;
    private String password;
}
