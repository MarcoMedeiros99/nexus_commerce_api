package com.marcomedeiros.nexus_commerce_api.model.access;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(nullable = false, unique = true)
    private String nameRole;

    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    @PrePersist
    private void generateAccessCode() {
        if (accessCode == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#RLN-" + randomHash;
        }
    }
}
