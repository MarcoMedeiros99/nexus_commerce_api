package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    // Essencial para verificar se uma regra já existe antes de criar
    Optional<Role> findByNameRole(String nameRole);

    // Útil para buscas ignorando maiúsculas/minúsculas (ex: "ADMIN" vs "admin")
    Optional<Role> findByNameRoleIgnoreCase(String nameRole);
}
