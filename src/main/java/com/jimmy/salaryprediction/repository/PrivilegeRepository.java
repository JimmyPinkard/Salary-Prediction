package com.jimmy.salaryprediction.repository;

import com.jimmy.salaryprediction.model.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findRoleByName(final String name);
}
