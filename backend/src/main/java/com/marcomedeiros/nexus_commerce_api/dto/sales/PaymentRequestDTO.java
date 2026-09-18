package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequestDTO(

        @NotNull(message = "O ID do pedido Ã© obrigatÃ³rio")
        Long idOrder,

        @NotNull(message = "O mÃ©todo de pagamento Ã© obrigatÃ³rio")
        PaymentMethod paymentMethod,

        @NotNull(message = "O valor pago Ã© obrigatÃ³rio")
        @Positive(message = "O valor pago deve ser positivo")
        BigDecimal amountPaid) {
}
