package com.marcomedeiros.nexus_commerce_api.repository.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Busca o produto pelo código interno do sistema (#PROD-XXXXXX)
    Optional<Product> findByAccessCode(String accessCode);

    // Para a barra de busca: pesquisa produtos que contenham o texto no nome
    List<Product> findByNameContainingIgnoreCase(String name);

    // Útil para listar produtos de uma categoria específica pelo nome dela
    List<Product> findByCategoriesNameIgnoreCase(String categoryName);
}
