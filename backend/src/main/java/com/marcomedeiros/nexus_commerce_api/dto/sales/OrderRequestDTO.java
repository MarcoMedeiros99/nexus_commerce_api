package com.marcomedeiros.nexus_commerce_api.dto.sales;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Set;

public record OrderRequestDTO(

        @NotNull(message = "O valor do frete Ã© obrigatÃ³rio")
        BigDecimal freightValue,

        @NotNull(message = "O ID do usuÃ¡rio Ã© obrigatÃ³rio")
        Long idUser,

        Long idCoupon,

        @NotNull(message = "O ID do endereÃ§o de entrega Ã© obrigatÃ³rio")
        Long idAddress,

        @NotNull(message = "O pedido deve ter ao menos um item")
        @Size(min = 1, message = "Informe pelo menos um item")
        @Valid
        Set<OrderItemRequestDTO> items) {
}
