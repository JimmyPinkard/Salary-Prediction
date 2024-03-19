package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.controller.request.CreateUserRequest;
import com.jimmy.salaryprediction.controller.request.LoginRequest;
import com.jimmy.salaryprediction.model.User;
import com.jimmy.salaryprediction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(CreateUserRequest createUserRequest) throws Exception {
        if(userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new Exception("User exists");
        }
        User user = new User();
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setRoles(createUserRequest.getRoles());
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User login(LoginRequest loginRequest) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if(optionalUser.isEmpty()) {
            throw new Exception("User doesn't exist!");
        }
        User user = optionalUser.get();
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new Exception("Password is wrong!");
        }
        return user;
    }
}
