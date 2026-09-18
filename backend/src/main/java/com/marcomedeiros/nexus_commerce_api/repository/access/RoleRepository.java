package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameRole(String nameRole);
    Optional<Role> findByNameRoleIgnoreCase(String nameRole);
}
