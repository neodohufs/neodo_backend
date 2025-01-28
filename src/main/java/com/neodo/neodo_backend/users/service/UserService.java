package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserResponse signupUser(UserCreateRequest request);
    Optional<UserEntity> findOne(Long userId);
}
