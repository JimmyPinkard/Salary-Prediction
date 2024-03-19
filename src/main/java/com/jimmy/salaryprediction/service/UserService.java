package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CreateUserRequest;
import com.jimmy.salaryprediction.controller.request.LoginRequest;
import com.jimmy.salaryprediction.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(CreateUserRequest createUserRequest) throws Exception;
    User login(LoginRequest loginRequest) throws Exception;
}
