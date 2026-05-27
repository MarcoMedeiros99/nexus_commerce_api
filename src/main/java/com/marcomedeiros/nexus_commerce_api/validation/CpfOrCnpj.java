package com.marcomedeiros.nexus_commerce_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // Indica que a anotação vai em cima da CLASSE (ou Record)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfOrCnpjValidator.class) // Aponta para a classe que fará a lógica
@Documented
public @interface CpfOrCnpj {
    String message() default "Documento inválido para o tipo de pessoa selecionado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
