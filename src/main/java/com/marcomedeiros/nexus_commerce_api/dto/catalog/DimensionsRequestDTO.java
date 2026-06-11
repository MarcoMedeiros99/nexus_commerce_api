package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DimensionsRequestDTO(
        @NotNull(message = "O peso é obrigatório")
        @PositiveOrZero(message = "O peso não pode ser negativo")
        Double weight,

        @NotNull(message = "O comprimento é obrigatório")
        @PositiveOrZero
        Double length,

        @NotNull(message = "A largura é obrigatória")
        @PositiveOrZero
        Double width,

        @NotNull(message = "A altura é obrigatória")
        @PositiveOrZero
        Double height) {
}
