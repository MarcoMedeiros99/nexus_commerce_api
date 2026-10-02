package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.model.access.Role;

public record RoleResponseDTO(
        String roleName,
        String accessCode) {

    public RoleResponseDTO(Role role) {
        this(
                role.getNameRole(),
                role.getAccessCode());
    }
}
