package com.marcomedeiros.nexus_commerce_api.repository.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByAccessCode(String accessCode);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoriesNameIgnoreCase(String categoryName);
}
