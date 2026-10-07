package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;

public record UserResponseDTO(
    String accessCode,
    String name,
    String email,
    String document,
    String phone,
    TypePerson typePerson){

    public UserResponseDTO(User user) {
            this(
                    user.getAccessCode(),
                    user.getName(),
                    user.getEmail(),
                    user.getDocument(),
                    user.getPhone(),
                    user.getTypePerson()
            );
        }
}
