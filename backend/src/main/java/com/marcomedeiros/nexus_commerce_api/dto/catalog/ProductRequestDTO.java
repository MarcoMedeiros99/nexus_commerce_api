package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRequestDTO(
        @NotBlank(message = "O nome Ã© obrigatÃ³rio")
        String name,

        @NotBlank(message = "A descriÃ§Ã£o Ã© obrigatÃ³ria")
        String description,

        @NotNull(message = "O preÃ§o Ã© obrigatÃ³rio")
        @Positive
        BigDecimal price,

        @NotNull(message = "As dimensÃµes sÃ£o obrigatÃ³rias")
        @Valid
        DimensionsRequestDTO dimensions,

        @NotNull(message = "O produto deve pertencer a pelo menos uma categoria")
        @Size(min = 1, message = "Informe pelo menos um cÃ³digo de categoria")
        Set<String>categoryCodes) {
}
