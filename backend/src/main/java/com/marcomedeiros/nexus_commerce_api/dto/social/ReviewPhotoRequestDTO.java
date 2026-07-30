package com.marcomedeiros.nexus_commerce_api.dto.social;

import jakarta.validation.constraints.NotBlank;

public record ReviewPhotoRequestDTO(

        @NotBlank(message = "Image URL is required")
        String imageUrl

) {}
