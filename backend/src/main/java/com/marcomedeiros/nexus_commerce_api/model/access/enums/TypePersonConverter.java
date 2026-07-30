package com.marcomedeiros.nexus_commerce_api.model.access.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

// Indica que esta classe atua como um conversor customizado no JPA.
@Converter(autoApply = true)
public class TypePersonConverter implements AttributeConverter<TypePerson, Integer> {

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public Integer convertToDatabaseColumn(TypePerson attribute) {
        if (attribute == null) return null;
        return attribute.getCode(); // Pega o seu código 1 ou 2 para salvar no banco
    }

    // Indica que o metodo abaixo esta sobrescrevendo um metodo da classe pai ou interface.
    @Override
    public TypePerson convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return TypePerson.valueOf(dbData); // Usa o seu método para ler do banco
    }
}
