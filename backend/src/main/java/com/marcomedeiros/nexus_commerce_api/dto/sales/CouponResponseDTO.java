package com.marcomedeiros.nexus_commerce_api.dto.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Coupon;

import java.time.LocalDateTime;

public record CouponResponseDTO(

        Long idCoupon,
        String nameCode,
        Integer discountPercentage,
        LocalDateTime expiryDate,
        Boolean active) {

    public CouponResponseDTO(Coupon coupon) {
        this(
                coupon.getIdCoupon(),
                coupon.getNameCode(),
                coupon.getDiscountPercentage(),
                coupon.getExpiryDate(),
                coupon.getActive());
    }
}
