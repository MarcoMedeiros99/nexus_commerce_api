package com.marcomedeiros.nexus_commerce_api.model.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    NOT_INFORMED(0, "Não Informado"),
    WAITING_PAYMENT(1, "Aguardando Pagamento"),
    PAID(2, "Pago"),
    SHIPPED(3, "Enviado"),
    DELIVERED(4, "Entregue"),
    CANCELED(5, "Cancelado");

    private final Integer code;
    private final String description;

    public static OrderStatus toEnum(Integer code) {
        if (code == null) return null;
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code: " + code);
    }
}
