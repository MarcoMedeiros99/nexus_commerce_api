package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    @Builder.Default
    @ManyToMany(mappedBy = "categories") // mappedBy indica que quem "manda" na relação é a classe Product
    private Set<Product> products = new HashSet<>();

    @PrePersist
    private void generateAccessCode(){
        if (accessCode == null){
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#CAT-" + randomHash;
        }
    }
}