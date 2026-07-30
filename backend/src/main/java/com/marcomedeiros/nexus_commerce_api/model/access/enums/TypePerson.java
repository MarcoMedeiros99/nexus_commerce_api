package com.marcomedeiros.nexus_commerce_api.model.access.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypePerson {
    INDIVIDUAL(1, "Pessoa Física"),
    CORPORATE(2, "Pessoa Jurídica");

    private Integer code;
    private String description;

    public static TypePerson valueOf(int code){
        for (TypePerson value : TypePerson.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TypePerson code");
    }
}