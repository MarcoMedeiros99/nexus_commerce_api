package com.marcomedeiros.nexus_commerce_api.repository.catalog;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Busca categoria pelo código (#CAT-XXXXXX)
    Optional<Category> findByAccessCode(String accessCode);

    // Evita duplicatas ao criar novas categorias
    Optional<Category> findByNameIgnoreCase(String name);
}
