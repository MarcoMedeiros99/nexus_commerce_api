package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    private Long idProduct;

    @PositiveOrZero
    @Column(nullable = false)
    private int  itemQuantity;

    @PositiveOrZero
    @Column(nullable = false)
    private int minQuantity;

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal priceCost;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_product")
    private Product product;
}
