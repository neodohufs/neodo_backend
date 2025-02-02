package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
