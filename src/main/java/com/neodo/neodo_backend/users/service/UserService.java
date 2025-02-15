package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;

public interface UserService {
    UserResponse signup(UserCreateRequest request);
    UserResponse get(UserEntity user);
}
