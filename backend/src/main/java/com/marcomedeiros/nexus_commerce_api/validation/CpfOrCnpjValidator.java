package com.marcomedeiros.nexus_commerce_api.validation;

import com.marcomedeiros.nexus_commerce_api.dto.access.UserRequestDTO;
import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, UserRequestDTO> {
    @Override
    public boolean isValid(UserRequestDTO dto, ConstraintValidatorContext context) {

        if (dto.document() == null || dto.typePerson() == null) {
            return true;
        }

        boolean isValid = false;

        if (dto.typePerson() == TypePerson.INDIVIDUAL) {
            isValid = DocumentValidator.isValidCpf(dto.document());
        } else if (dto.typePerson() == TypePerson.CORPORATE) {
            isValid = DocumentValidator.isValidCnpj(dto.document());
        }
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("document")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

