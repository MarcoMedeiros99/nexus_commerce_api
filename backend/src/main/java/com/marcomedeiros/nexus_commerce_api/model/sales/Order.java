package com.marcomedeiros.nexus_commerce_api.model.sales;

import com.marcomedeiros.nexus_commerce_api.model.access.User;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.OrderStatus;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters.OrderStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_order")
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
public class Order implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalValue;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal finalValue;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal freightValue;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_user")
    private User user;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_coupon")
    private Coupon coupon;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Define um relacionamento de Um para Muitos (ex: Um pedido para Muitos itens).
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    // Indica que os dados de uma classe @Embeddable farão parte desta tabela.
    @Embedded
    private DeliveryAddress deliveryAddress;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Define um relacionamento de Um para Muitos (ex: Um pedido para Muitos itens).
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    // Aplica um conversor (Converter) para transformar os dados entre a aplicação e o banco.
    @Convert(converter = OrderStatusConverter.class)
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    private void generateAccessCode() {
        if (this.accessCode == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#ORD-" + randomHash;
        }
        if (this.orderStatus == null) {
            this.orderStatus = OrderStatus.NOT_INFORMED;
        }
    }
}