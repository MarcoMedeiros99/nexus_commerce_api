package com.marcomedeiros.nexus_commerce_api.dto.access.support;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.util.MaskUtils;

public record UserSupportResponseDTO(
        String accessCode,
        String name,
        String email,
        String document,
        String phone,
        TypePerson typePerson) {

    public UserSupportResponseDTO(User user) {
        this(
                user.getAccessCode(),
                user.getName(),
                MaskUtils.maskEmail(user.getEmail()),
                MaskUtils.maskDocument(user.getDocument()),
                MaskUtils.maskPhone(user.getPhone()),
                user.getTypePerson());
    }
}
