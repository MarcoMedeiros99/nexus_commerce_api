package com.marcomedeiros.nexus_commerce_api.model.catalog;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

// Define uma classe cujos atributos podem ser embutidos como colunas em outra tabela.
@Embeddable
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
public class DimensionsProduct {

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Double weight;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Double length;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Double width;

    // Validação: Garante que o número seja zero ou positivo.
    @PositiveOrZero
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(nullable = false)
    private Double height;
}
