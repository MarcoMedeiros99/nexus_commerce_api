package com.marcomedeiros.nexus_commerce_api.dto.social;

import java.time.LocalDateTime;

public record ProductFavoriteResponseDTO(

        Long idFavorite,
        Long userId,
        String userName,
        Long productId,
        String productName,
        LocalDateTime addFavorite

) {}
