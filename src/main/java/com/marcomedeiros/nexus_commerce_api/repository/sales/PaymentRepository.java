package com.marcomedeiros.nexus_commerce_api.repository.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByAccessCode(String accessCode);
    List<Payment> findByOrderIdOrder(Long idOrder);
}
