package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_category")
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
public class Category implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Define um relacionamento de Muitos para Muitos.
    @ManyToMany(mappedBy = "categories") // mappedBy indica que quem "manda" na relação é a classe Product
    private Set<Product> products = new HashSet<>();

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    private void generateAccessCode(){
        if (accessCode == null){
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#CAT-" + randomHash;
        }
    }
}