package com.neodo.neodo_backend.users.dto.response;

import com.neodo.neodo_backend.users.infrastructure.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long userId;
    private String username;
    private String email;

    private UserResponse(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public static UserResponse from(UserEntity user){
        return new UserResponse(user.getUserId(), user.getUsername(), user.getEmail());
    }
}
