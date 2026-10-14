package com.marcomedeiros.nexus_commerce_api.repository.access;

import com.marcomedeiros.nexus_commerce_api.model.access.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    // Retorna as últimas 3 senhas do usuário (mais recentes primeiro)
    @Query("SELECT ph FROM PasswordHistory ph WHERE ph.user.idUser = :userId " +
           "ORDER BY ph.createdAt DESC LIMIT 3")
    List<PasswordHistory> findLast3ByUserId(@Param("userId") Long userId);

    // Conta quantas entradas existem para limpar o excesso depois
    long countByUserIdUser(Long userId);
}
