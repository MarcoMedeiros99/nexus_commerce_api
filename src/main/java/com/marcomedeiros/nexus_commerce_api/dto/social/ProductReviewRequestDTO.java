package com.marcomedeiros.nexus_commerce_api.dto.social;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record ProductReviewRequestDTO(

        @NotNull(message = "User ID is required")
        Long userId,

        @NotNull(message = "Product ID is required")
        Long productId,

        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,

        @Size(max = 2000, message = "Comment must not exceed 2000 characters")
        String comment,

        @Valid
        List<ReviewPhotoRequestDTO> photos

) {}
