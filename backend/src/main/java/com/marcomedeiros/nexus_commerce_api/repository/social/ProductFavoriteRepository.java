package com.marcomedeiros.nexus_commerce_api.repository.social;

import com.marcomedeiros.nexus_commerce_api.model.social.ProductFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite, Long> {

    // Lista todos os favoritos de um usuário
    List<ProductFavorite> findByUserIdUser(Long idUser);

    // Verifica se um usuário específico já favoritou um produto específico
    Optional<ProductFavorite> findByUserIdUserAndProductIdProduct(Long idUser, Long idProduct);

    // Útil para o botão de "coração" na vitrine
    boolean existsByUserIdUserAndProductIdProduct(Long idUser, Long idProduct);
}
