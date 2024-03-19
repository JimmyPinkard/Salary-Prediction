package com.jimmy.salaryprediction.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String email;
    private String password;
    private Set<String> roles;
}
