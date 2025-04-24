package com.david.springsecrest.repositories;

import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole role);
}