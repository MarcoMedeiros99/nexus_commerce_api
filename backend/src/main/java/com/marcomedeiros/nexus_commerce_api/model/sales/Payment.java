package com.marcomedeiros.nexus_commerce_api.model.sales;

import com.marcomedeiros.nexus_commerce_api.model.sales.enums.PaymentMethod;
import com.marcomedeiros.nexus_commerce_api.model.sales.enums.converters.PaymentMethodConverter;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_payment")
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
public class Payment implements Serializable{
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    // Define que este atributo é a Chave Primária (PK) da tabela.
    @Id
    // Configura como a chave primária será gerada automaticamente pelo banco.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    // Aplica um conversor (Converter) para transformar os dados entre a aplicação e o banco.
    @Convert(converter = PaymentMethodConverter.class)
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private LocalDateTime paymentDateTime;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne(fetch = FetchType.LAZY)
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    // Indica um método executado automaticamente ANTES da entidade ser salva pela primeira vez (INSERT).
    @PrePersist
    private void generateAccessCode() {
        // Garante o código de acesso único
        if (this.accessCode == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#PAY-" + randomHash;
        }

        // Garante a data e hora do pagamento
        if (this.paymentDateTime == null) {
            this.paymentDateTime = LocalDateTime.now();
        }

        // Garante um método de pagamento padrão se estiver nulo
        if (this.paymentMethod == null) {
            this.paymentMethod = PaymentMethod.NOT_INFORMED;
        }
    }

}
