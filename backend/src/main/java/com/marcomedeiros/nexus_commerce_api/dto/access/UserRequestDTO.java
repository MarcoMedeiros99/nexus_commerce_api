package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import com.marcomedeiros.nexus_commerce_api.validation.CpfOrCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@CpfOrCnpj
public record UserRequestDTO(

        @NotBlank(message = "O nome Ã© obrigatÃ³rio") String name,

        @NotBlank(message = "O documento (CPF ou CNPJ) Ã© obrigatÃ³rio") String document,

        @NotBlank(message = "O telefone Ã© obrigatÃ³rio") @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve conter apenas nÃºmeros e ter entre 10 e 11 dÃ­gitos") String phone,

        @Email(message = "Formato de e-mail invÃ¡lido. Ex: xxxxxx@xxxxx.com") String email,

        @NotNull(message = "O tipo de pessoa Ã© obrigatÃ³rio") TypePerson typePerson) {
}