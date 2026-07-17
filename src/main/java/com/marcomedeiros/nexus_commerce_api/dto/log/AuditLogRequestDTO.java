package com.marcomedeiros.nexus_commerce_api.dto.log;

import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuditLogRequestDTO(

                @NotNull(message = "O tipo de ação é obrigatorio") ActionType actionType,

                @Size(max = 2000, message = "A descricao não pode ultrapassar 2000 caracteres") String description,

                @Size(max = 45, message = "O endereco IP não pode ultrapassar 45 caracteres") String ipAddress) {
}
