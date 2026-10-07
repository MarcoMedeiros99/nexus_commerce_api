package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.OrderItem;

import java.math.BigDecimal;

public record OrderItemResponseDTO(

        String orderAccessCode,
        String productAccessCode,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal) {

    public OrderItemResponseDTO(OrderItem orderItem) {
        this(
                orderItem.getId().getOrder() != null ? orderItem.getId().getOrder().getAccessCode() : null,
                orderItem.getId().getProduct() != null ? orderItem.getId().getProduct().getAccessCode() : null,
                orderItem.getId().getProduct() != null ? orderItem.getId().getProduct().getName() : null,
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
    }
}
