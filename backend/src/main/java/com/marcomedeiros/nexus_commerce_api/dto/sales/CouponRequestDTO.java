package com.marcomedeiros.nexus_commerce_api.dto.sales;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CouponRequestDTO(

                @NotBlank(message = "O cÃ³digo do cupom Ã© obrigatÃ³rio") @Size(max = 20, message = "O cÃ³digo do cupom nÃ£o pode ultrapassar 20 caracteres") String nameCode,

                @NotNull(message = "O percentual de desconto Ã© obrigatÃ³rio") @Min(value = 1, message = "O desconto deve ser de no mÃ­nimo 1%") @Max(value = 100, message = "O desconto deve ser de no mÃ¡ximo 100%") Integer discountPercentage,

                @NotNull(message = "A data de expiraÃ§Ã£o Ã© obrigatÃ³ria") LocalDateTime expiryDate) {
}
