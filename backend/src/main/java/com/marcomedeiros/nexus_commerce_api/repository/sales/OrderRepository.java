package com.marcomedeiros.nexus_commerce_api.repository.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Busca o pedido pelo código externo (#ORD-XXXXXX)
    Optional<Order> findByAccessCode(String accessCode);

    // Lista pedidos por usuário (útil para o "Meus Pedidos")
    List<Order> findByUserIdUser(Long idUser);

    // Lista pedidos por status (ex: tudo que está WAITING_PAYMENT)
    List<Order> findByOrderStatus(OrderStatus status);
}
