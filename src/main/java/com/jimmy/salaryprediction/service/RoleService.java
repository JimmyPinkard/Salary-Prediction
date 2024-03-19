package com.jimmy.salaryprediction.service;

import com.jimmy.salaryprediction.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> getRoles();
    Set<Role> getRolesByNames(final Set<String> roleNames);
}
