package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.model.Role;
import com.jimmy.salaryprediction.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Set<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> getRolesByNames(final Set<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        for(final String roleName : roleNames) {
            roles.add(roleRepository.findRoleByName(roleName));
        }
        return roles;
    }
}
