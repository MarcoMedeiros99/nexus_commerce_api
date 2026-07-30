package com.marcomedeiros.nexus_commerce_api.dto.sales;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CouponRequestDTO(

                @NotBlank(message = "O código do cupom é obrigatório") @Size(max = 20, message = "O código do cupom não pode ultrapassar 20 caracteres") String nameCode,

                @NotNull(message = "O percentual de desconto é obrigatório") @Min(value = 1, message = "O desconto deve ser de no mínimo 1%") @Max(value = 100, message = "O desconto deve ser de no máximo 100%") Integer discountPercentage,

                @NotNull(message = "A data de expiração é obrigatória") LocalDateTime expiryDate) {
}
