package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CreateUserRequest;
import com.jimmy.salaryprediction.controller.request.LoginRequest;
import com.jimmy.salaryprediction.controller.response.CreateUserResponse;
import com.jimmy.salaryprediction.controller.response.LoginResponse;
import com.jimmy.salaryprediction.exceptions.ApiError;
import com.jimmy.salaryprediction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(final CreateUserRequest createUserRequest) throws Exception {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        createUserResponse.fromUser(userService.createUser(createUserRequest));
        return ResponseEntity.ok(createUserResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(final LoginRequest loginRequest) throws Exception {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.fromUser(userService.login(loginRequest));
        return ResponseEntity.ok(loginResponse);
    }
}
