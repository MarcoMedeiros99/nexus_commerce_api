package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "A descrição é obrigatória")
        String description,

        @NotNull(message = "O preço é obrigatório")
        @Positive
        BigDecimal price,

        @NotNull(message = "As dimensões são obrigatórias")
        @Valid
        DimensionsRequestDTO dimensions,

        @NotNull(message = "O produto deve pertencer a pelo menos uma categoria")
        @Size(min = 1, message = "Informe pelo menos um código de categoria")
        Set<String>categoryCodes) {
}
