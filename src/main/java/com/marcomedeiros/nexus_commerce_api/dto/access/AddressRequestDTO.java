package com.marcomedeiros.nexus_commerce_api.dto.access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressRequestDTO(

        @NotBlank(message = "O endereço é obrigatório")
        String streetAddress,

        @NotBlank(message = "O numero é obrigatório")
        String number,

        @NotBlank(message = "O cidade é obrigatório")
        String city,

        @NotBlank(message = "O estado é obrigatório")
        String state,

        @NotBlank(message = "O bairro é obrigatório")
        String neighborhood,

        String complement,

        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
        String zipCode
) {
}
