package com.marcomedeiros.nexus_commerce_api.model.social;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product_favorite", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_user", "id_product"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    @EqualsAndHashCode.Include
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", nullable = false)
    @EqualsAndHashCode.Include
    private Product product;

    @Column(nullable = false)
    private LocalDateTime addFavorite;

    @PrePersist
    protected void onCreate() {
        this.addFavorite = LocalDateTime.now();
    }
}