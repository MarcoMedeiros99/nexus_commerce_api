package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Coupon;

import java.time.LocalDateTime;

public record CouponResponseDTO(

        Long idCoupon,
        String nameCode,
        com.marcomedeiros.nexus_commerce_api.model.sales.enums.DiscountType discountType,
        java.math.BigDecimal discountValue,
        LocalDateTime expiryDate,
        Boolean active) {

    public CouponResponseDTO(Coupon coupon) {
        this(
                coupon.getIdCoupon(),
                coupon.getNameCode(),
                coupon.getDiscountType(),
                coupon.getDiscountValue(),
                coupon.getExpiryDate(),
                coupon.getActive());
    }
}
