package com.marcomedeiros.nexus_commerce_api.repository.social;

import com.marcomedeiros.nexus_commerce_api.model.social.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {

    // Busca todas as fotos de uma avaliação específica
    List<ReviewPhoto> findByReviewIdReview(Long idReview);
}
