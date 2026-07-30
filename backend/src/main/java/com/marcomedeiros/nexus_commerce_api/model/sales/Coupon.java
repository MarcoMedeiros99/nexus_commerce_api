package com.marcomedeiros.nexus_commerce_api.model.sales;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_coupon")
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
public class Coupon implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCoupon;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, unique = true, length = 15)
    private String nameCode;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "discount_type", nullable = false)
    // Define como um Enum será salvo no banco de dados (STRING ou ORDINAL).
    @Enumerated(EnumType.STRING)
    private com.marcomedeiros.nexus_commerce_api.model.sales.enums.DiscountType discountType;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal discountValue;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private LocalDateTime expiryDate;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Boolean active = true;
}
