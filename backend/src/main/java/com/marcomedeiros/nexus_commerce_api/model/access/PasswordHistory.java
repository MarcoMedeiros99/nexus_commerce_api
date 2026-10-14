package com.marcomedeiros.nexus_commerce_api.model.access;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.marcomedeiros.nexus_commerce_api.model.access.users.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_password_history")
public class PasswordHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Senha criptografada armazenada no histórico
    @Column(name = "hashed_password", nullable = false, length = 254)
    private String hashedPassword;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
