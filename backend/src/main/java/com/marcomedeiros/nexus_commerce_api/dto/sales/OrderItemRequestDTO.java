package com.marcomedeiros.nexus_commerce_api.dto.sales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record OrderItemRequestDTO(

        @NotBlank(message = "O cÃ³digo de acesso do produto Ã© obrigatÃ³rio")
        String productAccessCode,

        @NotNull(message = "A quantidade Ã© obrigatÃ³ria")
        @PositiveOrZero(message = "A quantidade deve ser zero ou positiva")
        Integer quantity,

        @NotNull(message = "O preÃ§o unitÃ¡rio Ã© obrigatÃ³rio")
        @Positive(message = "O preÃ§o unitÃ¡rio deve ser positivo")
        BigDecimal unitPrice) {
}
