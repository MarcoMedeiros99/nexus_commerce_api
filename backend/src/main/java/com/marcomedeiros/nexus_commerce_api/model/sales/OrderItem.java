package com.marcomedeiros.nexus_commerce_api.model.sales;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.sales.pk.OrderItemPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

// Indica que esta classe é uma entidade mapeada no banco de dados (JPA).
@Entity
// Define o nome da tabela no banco de dados para esta entidade.
@Table(name = "tb_order_item")
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
public class OrderItem implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Define um valor padrao para este atributo ao instanciar a classe usando o Builder (Lombok).
    @Builder.Default
    // Define uma chave primária composta usando uma classe @Embeddable.
    @EmbeddedId // fala que o ID é classe composta OrderItemPK
    // Inclui especificamente este atributo na geração do equals() e hashCode().
    @EqualsAndHashCode.Include
    private OrderItemPK id = new OrderItemPK();

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Integer quantity;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private BigDecimal unitPrice;

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }
}
