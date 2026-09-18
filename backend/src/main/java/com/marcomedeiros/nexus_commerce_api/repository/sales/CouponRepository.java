package com.marcomedeiros.nexus_commerce_api.repository.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByNameCodeIgnoreCaseAndActiveTrue(String nameCode);
}
