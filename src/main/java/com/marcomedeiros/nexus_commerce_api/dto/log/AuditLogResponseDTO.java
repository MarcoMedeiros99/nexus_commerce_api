package com.marcomedeiros.nexus_commerce_api.dto.log;

import com.marcomedeiros.nexus_commerce_api.model.log.AuditLog;
import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;

import java.time.LocalDateTime;

public record AuditLogResponseDTO(

        Long idLog,
        LocalDateTime dateTime,
        String userName,
        ActionType actionType,
        String description,
        String ipAddress) {

    public AuditLogResponseDTO(AuditLog log) {
        this(
                log.getIdLog(),
                log.getDateTime(),
                log.getUser() != null ? log.getUser().getName() : null,
                log.getActionType(),
                log.getDescription(),
                log.getIpAddress()
        );
    }
}
