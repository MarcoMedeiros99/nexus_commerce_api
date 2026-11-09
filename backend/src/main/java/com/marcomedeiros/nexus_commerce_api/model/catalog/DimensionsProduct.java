package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DimensionsProduct {
    public DimensionsProduct() {
    }

    @PositiveOrZero
    @Column(nullable = false)
    private Double weight;
    @PositiveOrZero
    @Column(nullable = false)
    private Double length;
    @PositiveOrZero
    @Column(nullable = false)
    private Double width;
    @PositiveOrZero
    @Column(nullable = false)
    private Double height;
}
