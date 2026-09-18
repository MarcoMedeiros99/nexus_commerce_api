package com.marcomedeiros.nexus_commerce_api.model.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum PaymentMethod {
    NOT_INFORMED(0, "NÃ£o Informado"),
    CREDIT_CARD(1, "CartÃ£o de CrÃ©dito"),
    DEBIT_CARD(2, "CartÃ£o de DÃ©bito"),
    PIX(3, "PIX"),
    BOLETO(4, "Boleto"),
    BANK_TRANSFER(5, "TransferÃªncia BancÃ¡ria"),
    CASH(6, "Dinheiro em EspÃ©cie");

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
