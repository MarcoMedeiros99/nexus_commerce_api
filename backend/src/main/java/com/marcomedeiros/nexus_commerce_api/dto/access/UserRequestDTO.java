package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.validation.CpfOrCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@CpfOrCnpj
public record UserRequestDTO(

        @NotBlank(message = "O nome é obrigatorio") String name,

        @NotBlank(message = "O documento (CPF ou CNPJ) é obrigatorio") String document,

        @NotBlank(message = "O telefone é obrigatorio") @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve conter apenas nÃºmeros e ter entre 10 e 11 dÃ­gitos") String phone,

        @Email(message = "Formato de e-mail invalido. Ex: xxxxxx@xxxxx.com") String email,

        @NotNull(message = "O tipo de pessoa é obrigatorio") TypePerson typePerson) {
}