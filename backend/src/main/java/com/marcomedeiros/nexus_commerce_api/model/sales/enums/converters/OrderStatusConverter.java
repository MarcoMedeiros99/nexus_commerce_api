package com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Indica que esta classe atua como um conversor customizado no JPA.
@Converter(autoApply = true) // O autoApply=true garante que o JPA aplique em todos os campos OrderStatus
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public Integer convertToDatabaseColumn(OrderStatus status) {
        return (status == null) ? null : status.getCode();
    }

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        return (code == null) ? null : OrderStatus.toEnum(code);
    }
}
