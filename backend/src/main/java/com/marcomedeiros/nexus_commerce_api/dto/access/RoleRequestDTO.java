package com.marcomedeiros.nexus_commerce_api.dto.access;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(
                @NotBlank(message = "O nome do cargo é obrigatório") String nameRole) {
}
