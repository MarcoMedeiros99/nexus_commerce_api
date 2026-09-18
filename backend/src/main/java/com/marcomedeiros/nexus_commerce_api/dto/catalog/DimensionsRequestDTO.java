package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DimensionsRequestDTO(
        @NotNull(message = "O peso Ã© obrigatÃ³rio")
        @PositiveOrZero(message = "O peso nÃ£o pode ser negativo")
        Double weight,

        @NotNull(message = "O comprimento Ã© obrigatÃ³rio")
        @PositiveOrZero
        Double length,

        @NotNull(message = "A largura Ã© obrigatÃ³ria")
        @PositiveOrZero
        Double width,

        @NotNull(message = "A altura Ã© obrigatÃ³ria")
        @PositiveOrZero
        Double height) {
}
