package com.marcomedeiros.nexus_commerce_api.model.access.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
@Converter(autoApply = true)
public class TypePersonConverter implements AttributeConverter<TypePerson, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TypePerson attribute) {
        if (attribute == null) return null;
        return attribute.getCode(); // Pega o seu cÃ³digo 1 ou 2 para salvar no banco
    }
    @Override
    public TypePerson convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return TypePerson.valueOf(dbData); // Usa o seu mÃ©todo para ler do banco
    }
}
