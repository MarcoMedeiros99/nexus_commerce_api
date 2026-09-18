package com.marcomedeiros.nexus_commerce_api.repository.social;

import com.marcomedeiros.nexus_commerce_api.model.social.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByProductIdProduct(Long idProduct);
    List<ProductReview> findByProductIdProductAndRating(Long idProduct, Integer rating);
    @Query("SELECT AVG(r.rating) FROM ProductReview r WHERE r.product.idProduct = :idProduct")
    Double getAverageRatingByProductId(Long idProduct);
}
