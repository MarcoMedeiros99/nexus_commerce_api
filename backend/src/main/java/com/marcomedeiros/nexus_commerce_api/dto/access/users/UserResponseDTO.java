package com.marcomedeiros.nexus_commerce_api.dto.access.users;

import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.model.access.users.AbstractUser;

public record UserResponseDTO(
                String accessCode,
                String name,
                String email,
                String document,
                String phone,
                TypePerson typePerson) {

        public UserResponseDTO(AbstractUser user) {
                this(
                                user.getAccessCode(),
                                user.getName(),
                                user.getEmail(),
                                user.getDocument(),
                                user.getPhone(),
                                user.getTypePerson());
        }
}
