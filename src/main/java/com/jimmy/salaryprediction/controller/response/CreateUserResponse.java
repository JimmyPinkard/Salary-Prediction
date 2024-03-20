package com.jimmy.salaryprediction.controller.response;

import com.jimmy.salaryprediction.model.Role;
import com.jimmy.salaryprediction.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private String email;
    private Set<String> roles;

    public void fromUser(final User user) {
        setEmail(user.getEmail());
        setRoles(new HashSet<>());
        for(Role role : user.getRoles()) {
            this.roles.add(role.getName());
        }
    }
}
