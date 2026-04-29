package com.marcomedeiros.nexus_commerce_api.repository.social;

import com.marcomedeiros.nexus_commerce_api.model.social.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    // Busca todas as avaliações de um produto específico
    List<ProductReview> findByProductIdProduct(Long idProduct);

    // Busca avaliações por nota (ex: filtrar apenas as de 5 estrelas)
    List<ProductReview> findByProductIdProductAndRating(Long idProduct, Integer rating);

    // JPQL para calcular a média de notas de um produto
    @Query("SELECT AVG(r.rating) FROM ProductReview r WHERE r.product.idProduct = :idProduct")
    Double getAverageRatingByProductId(Long idProduct);
}
