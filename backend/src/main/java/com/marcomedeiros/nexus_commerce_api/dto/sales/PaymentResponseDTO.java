package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Payment;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(

        Long idPayment,
        String accessCode,
        PaymentMethod paymentMethod,
        LocalDateTime paymentDateTime,
        BigDecimal amountPaid,
        String orderAccessCode) {

    public PaymentResponseDTO(Payment payment) {
        this(
                payment.getIdPayment(),
                payment.getAccessCode(),
                payment.getPaymentMethod(),
                payment.getPaymentDateTime(),
                payment.getAmountPaid(),
                payment.getOrder() != null ? payment.getOrder().getAccessCode() : null
        );
    }
}
