package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_stock")
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
public class Stock implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    private Long idProduct;

    // Cria um controle de versão para Optimistic Locking (previne concorrência de dados).
    @Version
    private Long version;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private int itemQuantity;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private int minQuantity;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private BigDecimal priceCost;

    // Define um relacionamento de Um para Um.
    @OneToOne
    // Indica que esta entidade compartilha a mesma Chave Primária da entidade relacionada.
    @MapsId
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_product")
    private Product product;
}
