package com.marcomedeiros.nexus_commerce_api.model.social;

import jakarta.persistence.*;
import lombok.*;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_review_photo")
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
public class ReviewPhoto {

    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private String imageUrl;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_review", nullable = false)
    private ProductReview review;
}