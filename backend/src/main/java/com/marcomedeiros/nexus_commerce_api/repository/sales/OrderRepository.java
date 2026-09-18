package com.marcomedeiros.nexus_commerce_api.repository.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByAccessCode(String accessCode);
    List<Order> findByUserIdUser(Long idUser);
    List<Order> findByOrderStatus(OrderStatus status);
}
