package com.marcomedeiros.nexus_commerce_api.model.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    NOT_INFORMED(0, "Não Informado"),
    CREDIT_CARD(1, "Cartão de Crédito"),
    DEBIT_CARD(2, "Cartão de Débito"),
    PIX(3, "PIX"),
    BOLETO(4, "Boleto"),
    BANK_TRANSFER(5, "Transferência Bancária"),
    CASH(6, "Dinheiro em Espécie");

    private final Integer code;
    private final String description;

    public static PaymentMethod toEnum(Integer code) {
        if (code == null) return null;
        for (PaymentMethod value : PaymentMethod.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentMethod code: " + code);
    }
}
