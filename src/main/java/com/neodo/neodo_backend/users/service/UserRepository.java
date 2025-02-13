package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
