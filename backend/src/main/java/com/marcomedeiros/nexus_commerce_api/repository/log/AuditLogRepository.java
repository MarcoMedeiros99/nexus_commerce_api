package com.marcomedeiros.nexus_commerce_api.repository.log;

import com.marcomedeiros.nexus_commerce_api.model.log.AuditLog;
import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    // Busca logs de um usuário específico
    List<AuditLog> findByUserIdUser(Long idUser);

    // Busca por tipo de ação (ex: todos os LOGIN_FALHA)
    List<AuditLog> findByActionType(ActionType actionType);

    // Busca logs em um intervalo de tempo (essencial para relatórios)
    List<AuditLog> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    // Busca logs de um IP específico (segurança)
    List<AuditLog> findByIpAddress(String ipAddress);
}
