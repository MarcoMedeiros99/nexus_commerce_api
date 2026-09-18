package com.marcomedeiros.nexus_commerce_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CpfOrCnpjValidator.class) // Aponta para a classe que farÃ¡ a lÃ³gica
@Documented
public @interface CpfOrCnpj {
    String message() default "Documento invÃ¡lido para o tipo de pessoa selecionado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
