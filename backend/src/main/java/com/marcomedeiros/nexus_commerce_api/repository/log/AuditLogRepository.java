package com.marcomedeiros.nexus_commerce_api.repository.log;

import com.marcomedeiros.nexus_commerce_api.model.log.AuditLog;
import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUserIdUser(Long idUser);
    List<AuditLog> findByActionType(ActionType actionType);
    List<AuditLog> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<AuditLog> findByIpAddress(String ipAddress);
}
