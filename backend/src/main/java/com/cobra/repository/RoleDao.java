package com.cobra.repository;

import com.cobra.enums.ERole;
import com.cobra.models.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao {
    Optional<Role> findByName(ERole role);
}
