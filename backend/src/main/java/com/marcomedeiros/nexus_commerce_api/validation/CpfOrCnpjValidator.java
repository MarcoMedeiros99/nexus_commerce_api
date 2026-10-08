package com.marcomedeiros.nexus_commerce_api.validation;

import com.marcomedeiros.nexus_commerce_api.dto.access.UserRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidator implements ConstraintValidator<CpfOrCnpj, UserRequestDTO> {
    @Override
    public boolean isValid(UserRequestDTO dto, ConstraintValidatorContext context) {

        if (dto == null || dto.document() == null) {
            return true;
        }

        boolean isValid = DocumentValidator.isValid(dto.document());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("document")
                    .addConstraintViolation();
        }

        return isValid;
    }
}

