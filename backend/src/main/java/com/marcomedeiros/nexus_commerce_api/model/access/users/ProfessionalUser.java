package com.marcomedeiros.nexus_commerce_api.model.access.users;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "tb_professional_user")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class ProfessionalUser extends AbstractUser {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfessionalUser;

    @Override
    @PrePersist
    protected void generateAccessCode() {
        if (getAccessCode() == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            setAccessCode("#" + (getRole() != null ? getRole().toString() : "PRO") + "-" + randomHash);
        }
    }
}
