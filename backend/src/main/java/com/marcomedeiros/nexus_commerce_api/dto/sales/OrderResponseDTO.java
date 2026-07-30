package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderResponseDTO(

        Long idOrder,
        String accessCode,
        BigDecimal totalValue,
        BigDecimal finalValue,
        BigDecimal freightValue,
        OrderStatus orderStatus,
        Long idUser,
        CouponResponseDTO coupon,
        Long idAddress,
        Set<OrderItemResponseDTO> items,
        PaymentResponseDTO lastPayment) {

    public OrderResponseDTO(Order order) {
        this(
                order.getIdOrder(),
                order.getAccessCode(),
                order.getTotalValue(),
                order.getFinalValue(),
                order.getFreightValue(),
                order.getOrderStatus(),
                order.getUser() != null ? order.getUser().getIdUser() : null,
                order.getCoupon() != null ? new CouponResponseDTO(order.getCoupon()) : null,
                order.getAddress() != null ? order.getAddress().getIdAddress() : null,
                order.getItems().stream()
                        .map(OrderItemResponseDTO::new)
                        .collect(Collectors.toSet()),
                order.getPayments().isEmpty() ? null
                        : new PaymentResponseDTO(order.getPayments().get(order.getPayments().size() - 1))
        );
    }
}
