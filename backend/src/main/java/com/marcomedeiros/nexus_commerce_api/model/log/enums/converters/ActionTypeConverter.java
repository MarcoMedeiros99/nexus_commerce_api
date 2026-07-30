package com.marcomedeiros.nexus_commerce_api.model.log.enums.converters;

import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Indica que esta classe atua como um conversor customizado no JPA.
@Converter(autoApply = true) // O autoApply=true garante que o JPA aplique em todos os campos do ActionType
public class ActionTypeConverter implements AttributeConverter<ActionType, Integer> {

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public Integer convertToDatabaseColumn(ActionType status) {
        return (status == null) ? null : status.getCode();
    }

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public ActionType convertToEntityAttribute(Integer code) {
        return (code == null) ? null : ActionType.toEnum(code);
    }
}
