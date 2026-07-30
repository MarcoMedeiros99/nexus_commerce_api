package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequestDTO(

        @NotNull(message = "O ID do pedido é obrigatório")
        Long idOrder,

        @NotNull(message = "O método de pagamento é obrigatório")
        PaymentMethod paymentMethod,

        @NotNull(message = "O valor pago é obrigatório")
        @Positive(message = "O valor pago deve ser positivo")
        BigDecimal amountPaid) {
}
