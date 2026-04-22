package com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true) // O autoApply=true garante que o JPA aplique em todos os campos OrderStatus
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus status) {
        return (status == null) ? null : status.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        return (code == null) ? null : OrderStatus.toEnum(code);
    }
}
