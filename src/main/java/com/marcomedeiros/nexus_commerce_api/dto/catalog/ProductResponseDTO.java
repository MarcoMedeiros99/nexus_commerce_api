package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductResponseDTO(

        String accessCode,
        String name,
        String description,
        BigDecimal price,
        DimensionsResponseDTO dimensions,
        Set<String> categoryCodes) {

    public ProductResponseDTO(Product product){
        this(
                product.getAccessCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new DimensionsResponseDTO(product.getDimensionsProduct()),

                product.getCategories().stream()
                        .map(category -> category.getAccessCode())
                        .collect(Collectors.toSet())
        );
    }
}
