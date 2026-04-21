package com.marcomedeiros.nexus_commerce_api.model.sales;

import com.marcomedeiros.nexus_commerce_api.model.access.Address;
import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters.OrderStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "tb_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @Column(nullable = false)
    private BigDecimal finalValue;

    @Column(nullable = false)
    private BigDecimal freightValue;

    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_coupon")
    private Coupon coupon;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_address")
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    @Convert(converter = OrderStatusConverter.class)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @PrePersist
    private void generateAccessCode() {
        if (this.accessCode == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#ORD-" + randomHash;
        }
        if (this.orderStatus == null) {
            this.orderStatus = OrderStatus.WAITING_PAYMENT;


        }
    }
}