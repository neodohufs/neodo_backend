package com.neodo.neodo_backend.users.service;

import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;

public interface UserService {
    UserResponse signupUser(UserCreateRequest request);
}
