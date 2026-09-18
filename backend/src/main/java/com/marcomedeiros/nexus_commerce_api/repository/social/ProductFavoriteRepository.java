package com.marcomedeiros.nexus_commerce_api.repository.social;

import com.marcomedeiros.nexus_commerce_api.model.social.ProductFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite, Long> {
    List<ProductFavorite> findByUserIdUser(Long idUser);
    Optional<ProductFavorite> findByUserIdUserAndProductIdProduct(Long idUser, Long idProduct);
    boolean existsByUserIdUserAndProductIdProduct(Long idUser, Long idProduct);
}
