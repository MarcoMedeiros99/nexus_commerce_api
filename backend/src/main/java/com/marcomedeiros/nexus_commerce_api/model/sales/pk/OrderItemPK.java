package com.marcomedeiros.nexus_commerce_api.model.sales.pk;

import com.marcomedeiros.nexus_commerce_api.model.catalog.Product;
import com.marcomedeiros.nexus_commerce_api.model.sales.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

// Define uma classe cujos atributos podem ser embutidos como colunas em outra tabela.
@Embeddable // Indica que esta classe é um "pedaço" de outra
// Cria automaticamente os métodos get() para todos os atributos (Lombok).
@Getter
// Cria automaticamente os métodos set() para todos os atributos (Lombok).
@Setter
// Gera os métodos equals() e hashCode() automaticamente.
@EqualsAndHashCode
// Cria um construtor vazio automaticamente (exigência do JPA e útil para frameworks).
@NoArgsConstructor
// Cria um construtor com todos os atributos da classe.
@AllArgsConstructor
public class OrderItemPK implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_order")
    private Order order;

    // Define um relacionamento de Muitos para Um (ex: Muitos itens para Um pedido).
    @ManyToOne
    // Especifica a coluna de chave estrangeira (FK) usada no relacionamento.
    @JoinColumn(name = "id_product")
    private Product product;

}
