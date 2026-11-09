package com.marcomedeiros.nexus_commerce_api.dto.access.users;

import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.ProfessionalUser;

public record ProfessionalUserResponseDTO(
                String accessCode,
                String name,
                String email,
                String document,
                String phone,
                TypePerson typePerson,
                String roleName) {

        public ProfessionalUserResponseDTO(ProfessionalUser user) {
                this(
                                user.getAccessCode(),
                                user.getName(),
                                user.getEmail(),
                                user.getDocument(),
                                user.getPhone(),
                                user.getTypePerson(),
                                user.getRole() != null ? user.getRole().getNameRole() : null);
        }
}

