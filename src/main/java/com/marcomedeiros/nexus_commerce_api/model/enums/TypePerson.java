package com.marcomedeiros.nexus_commerce_api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypePerson {
    INDIVIDUAL(1),
    CORPORATE(2);

    private int code;

    public static TypePerson valueOf(int code){
        for (TypePerson value : TypePerson.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid TypePerson code");
    }
}