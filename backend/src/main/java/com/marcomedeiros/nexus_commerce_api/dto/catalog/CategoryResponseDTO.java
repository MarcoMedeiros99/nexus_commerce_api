package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Category;

public record CategoryResponseDTO(
        String name,
        String accessCode) {

    public CategoryResponseDTO(Category category){
        this(
                category.getName(),
                category.getAccessCode()
        );
    }
}
