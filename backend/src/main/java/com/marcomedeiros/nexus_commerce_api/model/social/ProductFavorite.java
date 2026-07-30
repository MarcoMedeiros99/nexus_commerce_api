package com.marcomedeiros.nexus_commerce_api.model.social;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_product_favorite", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_user", "id_product"})})
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
// Gera os métodos equals() e hashCode() automaticamente.
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductFavorite {

    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFavorite;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_user", nullable = false)
    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    private User user;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_product", nullable = false)
    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    private Product product;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private LocalDateTime addFavorite;

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    protected void onCreate() {
        this.addFavorite = LocalDateTime.now();
    }
}