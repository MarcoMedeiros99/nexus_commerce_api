package com.marcomedeiros.nexus_commerce_api.dto.access;

import com.marcomedeiros.nexus_commerce_api.validation.CpfOrCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@CpfOrCnpj
public record UserRequestDTO(

        @NotBlank(message = "O nome é obrigatorio") String name,

        @NotBlank(message = "O documento (CPF ou CNPJ) é obrigatorio") String document,

        @NotBlank(message = "O telefone é obrigatorio") @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve conter apenas números e ter entre 10 e 11 dígitos") String phone,

        @Email(message = "Formato de e-mail invalido. Ex: xxxxxx@xxxxx.com") String email,

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{10,}$",
                message = "A senha deve ter no mínimo 10 caracteres, contendo letras maiúsculas, minúsculas, números e pelo menos um caractere especial"
        )
        String password) {
}