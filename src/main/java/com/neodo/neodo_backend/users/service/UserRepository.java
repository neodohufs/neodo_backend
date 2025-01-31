package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(@NotBlank(message = "사용자 이름은 필수입니다.") @Email(message = "유효하지 않은 이메일 형식입니다.") String email);
}
