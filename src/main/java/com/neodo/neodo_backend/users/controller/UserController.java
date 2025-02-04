package com.neodo.neodo_backend.users.controller;

import com.neodo.neodo_backend.users.dto.request.UserCreateRequest;
import com.neodo.neodo_backend.users.dto.response.UserResponse;
import com.neodo.neodo_backend.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        // 서비스 호출 로직
        UserResponse response = userService.signup(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-page")
    public ResponseEntity<?> get(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserResponse response = userService.get(userDetails.getUser());
        return ResponseEntity.ok(response);
    }



}
