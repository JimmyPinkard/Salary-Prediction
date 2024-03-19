package com.jimmy.salaryprediction.repository;

import com.jimmy.salaryprediction.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(final String email);

    boolean existsByEmail(final String email);
}
