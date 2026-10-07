package com.marcomedeiros.nexus_commerce_api.dto.social;

import java.time.LocalDateTime;
import java.util.List;

public record ProductReviewResponseDTO(

        Long idReview,
        Integer rating,
        String comment,
        LocalDateTime reviewDate,
        Long userId,
        String userName,
        Long productId,
        String productName,
        List<ReviewPhotoResponseDTO> photos

) {}
