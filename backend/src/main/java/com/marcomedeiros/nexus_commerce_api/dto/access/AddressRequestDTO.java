package com.marcomedeiros.nexus_commerce_api.dto.access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequestDTO(

        @NotBlank(message = "O endereÃ§o Ã© obrigatÃ³rio")
        String streetAddress,

        @NotBlank(message = "O numero Ã© obrigatÃ³rio")
        String number,

        @NotBlank(message = "O cidade Ã© obrigatÃ³rio")
        String city,

        @NotBlank(message = "O estado Ã© obrigatÃ³rio")
        String state,

        @NotBlank(message = "O bairro Ã© obrigatÃ³rio")
        String neighborhood,

        String complement,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
        String zipCode
) {
}
