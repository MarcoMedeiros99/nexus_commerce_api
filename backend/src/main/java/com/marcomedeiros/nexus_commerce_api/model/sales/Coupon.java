package com.marcomedeiros.nexus_commerce_api.model.sales;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Coupon implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoupon;
    @Column(nullable = false, unique = true, length = 15)
    private String nameCode;
    @Column(name = "discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private com.marcomedeiros.nexus_commerce_api.model.sales.enums.DiscountType discountType;
    @Column(nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal discountValue;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;
}
