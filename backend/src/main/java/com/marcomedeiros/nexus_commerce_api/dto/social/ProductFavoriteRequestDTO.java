package com.marcomedeiros.nexus_commerce_api.dto.social;

import jakarta.validation.constraints.NotNull;

public record ProductFavoriteRequestDTO(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Product ID is required")
        Long productId

) {}
