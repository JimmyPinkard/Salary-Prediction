package com.jimmy.salaryprediction.controller.request;

import com.jimmy.salaryprediction.model.Role;
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
    private Set<Role> roles;
}
