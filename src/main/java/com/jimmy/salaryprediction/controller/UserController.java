package com.jimmy.salaryprediction.controller;

import com.jimmy.salaryprediction.controller.request.CreateUserRequest;
import com.jimmy.salaryprediction.controller.response.CreateUserResponse;
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
    public ResponseEntity<CreateUserResponse> createUser(final CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse = new CreateUserResponse();
        try {
            createUserResponse.fromUser(userService.createUser(createUserRequest));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(createUserResponse);
    }
}
