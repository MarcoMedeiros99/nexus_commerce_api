package com.marcomedeiros.nexus_commerce_api.model.sales;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.sales.pk.OrderItemPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId // fala que o ID é classe composta OrderItemPK
    @EqualsAndHashCode.Include
    private OrderItemPK id = new OrderItemPK();

    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }
}
