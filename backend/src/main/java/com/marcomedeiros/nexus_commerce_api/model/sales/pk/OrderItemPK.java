package com.marcomedeiros.nexus_commerce_api.model.sales.pk;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Embeddable // Indica que esta classe é um "pedaço" de outra
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

}
