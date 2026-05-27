package com.marcomedeiros.nexus_commerce_api.validation;

import com.marcomedeiros.nexus_commerce_api.dto.access.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, UserRequestDTO> {
    @Override
    public boolean isValid(UserRequestDTO dto, ConstraintValidatorContext context) {
        // Se algum dos dois for nulo, deixamos o @NotBlank e @NotNull cuidarem do erro
        if (dto.document() == null || dto.typePerson() == null) {
            return true;
        }

        boolean isValid = false;

        if (dto.typePerson() == TypePerson.INDIVIDUAL) { // Ajuste para o nome que está no seu Enum
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.initialize(null);
            isValid = cpfValidator.isValid(dto.document(), context);

        } else if (dto.typePerson() == TypePerson.CORPORATE) { // Ajuste para o nome do seu Enum
            CNPJValidator cnpjValidator = new CNPJValidator();
            cnpjValidator.initialize(null);
            isValid = cnpjValidator.isValid(dto.document(), context);
        }

        // Se for inválido, direcionamos a mensagem de erro especificamente para o campo "document"
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("document")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

