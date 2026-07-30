package com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.PaymentMethod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Indica que esta classe atua como um conversor customizado no JPA.
@Converter(autoApply = true) // O autoApply=true faz o JPA usar ele automaticamente em qualquer campo PaymentMethod
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, Integer> {

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public Integer convertToDatabaseColumn(PaymentMethod method) {
        return (method == null) ? null : method.getCode();
    }

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public PaymentMethod convertToEntityAttribute(Integer code) {
        return (code == null) ? null : PaymentMethod.toEnum(code);
    }
}