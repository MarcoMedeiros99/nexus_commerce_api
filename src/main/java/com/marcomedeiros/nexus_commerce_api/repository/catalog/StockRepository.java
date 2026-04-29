package com.marcomedeiros.nexus_commerce_api.repository.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Busca produtos que estão com estoque abaixo do mínimo configurado
    List<Stock> findByItemQuantityLessThan(int quantity);
}
