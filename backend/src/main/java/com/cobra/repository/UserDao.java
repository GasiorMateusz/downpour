package com.cobra.repository;

import com.cobra.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User save(User user);

    Optional<User> getById(String id);
}
