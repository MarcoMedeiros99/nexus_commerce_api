package com.marcomedeiros.nexus_commerce_api.dto.sales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record OrderItemRequestDTO(

        @NotBlank(message = "O código de acesso do produto é obrigatório")
        String productAccessCode,

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "A quantidade deve ser zero ou positiva")
        Integer quantity,

        @NotNull(message = "O preço unitário é obrigatório")
        @Positive(message = "O preço unitário deve ser positivo")
        BigDecimal unitPrice) {
}
