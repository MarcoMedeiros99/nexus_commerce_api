package com.marcomedeiros.nexus_commerce_api.repository.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import com.marcomedeiros.nexus_commerce_api.model.sales.OrderItem;
import com.marcomedeiros.nexus_commerce_api.model.sales.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

    // Busca todos os itens de um pedido específico
    List<OrderItem> findByIdOrder(Order order);
}
