package com.jimmy.salaryprediction.repository;

import com.jimmy.salaryprediction.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByName(final String name);
    Set<Role> findAll();
}
