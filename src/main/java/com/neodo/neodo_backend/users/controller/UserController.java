package com.neodo.neodo_backend.users.controller;

import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.security.service.UserDetailsImpl;
import com.neodo.neodo_backend.common.response.CommonResponse;
import com.neodo.neodo_backend.common.response.responseEnum.SuccessResponseEnum;
import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserCreateRequest request) {

        // 서비스 호출 로직
        UserResponse response = userService.signup(request);
        URI location = URI.create("api/users/" + response.getId());
        return ResponseEntity.created(location)
                .body(CommonResponse.<UserResponse>builder()
                        .data(response)
                        .response(SuccessResponseEnum.RESOURCES_CREATED)
                        .build());
    }

    @GetMapping("/my-page")
    public ResponseEntity<CommonResponse<UserResponse>> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponse response = userService.get(userDetails.getUser());
        return ResponseEntity.ok()
                .body(CommonResponse.<UserResponse>builder()
                        .response(SuccessResponseEnum.READ_USER_INFO)
                        .data(response)
                        .build());
    }
}
