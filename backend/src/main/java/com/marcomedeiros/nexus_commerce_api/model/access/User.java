package com.marcomedeiros.nexus_commerce_api.model.access;

import com.marcomedeiros.nexus_commerce_api.model.access.enums.TypePerson;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_user")
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
public class User implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private String name;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, unique = true, length = 18)
    private String document;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private String phone;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, unique = true)
    private String email;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, length = 254)
    private String password;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Define um relacionamento de Um para Muitos (ex: Um pedido para Muitos itens).
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_role")
    private Role role;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "type_person", nullable = false)
    private TypePerson typePerson;

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    private void generateAccessCode(){
        if (accessCode == null){
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#USR-" + randomHash;
        }
    }
}