package com.marcomedeiros.nexus_commerce_api.dto.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.DimensionsProduct;

public record DimensionsResponseDTO(
        Double weight,
        Double length,
        Double width,
        Double height
) {
    public DimensionsResponseDTO(DimensionsProduct dimensions) {
        this(
                dimensions.getWeight(),
                dimensions.getLength(),
                dimensions.getWidth(),
                dimensions.getHeight()
        );
    }
}
