package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByEmail(String email);

}
