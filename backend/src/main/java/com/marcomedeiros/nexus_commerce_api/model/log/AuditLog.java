package com.marcomedeiros.nexus_commerce_api.model.log;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.log.enums.ActionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_audit_log")
// Cria automaticamente os métodos get() para todos os atributos (Lombok).
@Getter
// Cria automaticamente os métodos set() para todos os atributos (Lombok).
@Setter
// Cria um construtor vazio automaticamente (exigência do JPA e útil para frameworks).
@NoArgsConstructor
// Cria um construtor com todos os atributos da classe.
@AllArgsConstructor
// Permite criar instâncias da classe usando o padrão Builder.
@Builder
public class AuditLog {

    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLog;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private LocalDateTime dateTime;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_user")
    private User user; // Quem fez a ação

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "action_type", nullable = false)
    private ActionType actionType;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(columnDefinition = "TEXT")
    private String description;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    protected void onCreate() {
        this.dateTime = LocalDateTime.now();
    }
}
