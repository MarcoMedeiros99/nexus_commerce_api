package com.marcomedeiros.nexus_commerce_api.dto.access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// DTO focado apenas nas informações necessárias para a API dos Correios
public record FreightRequestDTO(

                @NotBlank(message = "O CEP de destino é obrigatório") @Pattern(regexp = "\\d{8}", message = "O CEP deve conter apenas 8 números, digite somente os números. ") String cepDestino, // CEP
                                                                                                                                                                                                  // do
                                                                                                                                                                                                  // cliente

                @NotBlank(message = "O código do serviço é obrigatório (ex: 04014 para SEDEX, 04510 para PAC)") String codigoServico, // PAC,
                                                                                                                                      // SEDEX,
                                                                                                                                      // etc.

                @NotNull(message = "O peso é obrigatório, em kg") Double peso, // Peso em kg

                @NotNull(message = "O comprimento é obrigatório, em cm") Double comprimento, // Em cm

                @NotNull(message = "A altura é obrigatória, em cm") Double altura, // Em cm

                @NotNull(message = "A largura é obrigatória, em cm") Double largura // Em cm
) {
}
