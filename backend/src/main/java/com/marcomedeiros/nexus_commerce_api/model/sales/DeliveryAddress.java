package com.marcomedeiros.nexus_commerce_api.model.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

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
public class DeliveryAddress implements Serializable {
    // Indica que este campo faz parte do mecanismo de serializacao da classe.
    @Serial
    private static final long serialVersionUID = 1L;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_street_address", nullable = false, length = 150)
    private String streetAddress;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Validação: Garante que o tamanho da string/coleção esteja dentro de um limite.
    @Size(max = 10)
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_number", nullable = false)
    private String number;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_city", nullable = false, length = 60)
    private String city;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Validação: Garante que o tamanho da string/coleção esteja dentro de um limite.
    @Size(min = 2, max = 2)
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_state", nullable = false)
    private String state;

    // Validação: Garante que a string não seja nula nem vazia.
    @NotBlank
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_neighborhood", nullable = false)
    private String neighborhood;

    // Validação: Garante que a string siga um formato específico (Regex).
    @Pattern(regexp = "\\d{5}-\\d{3}")
    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_zip_code", nullable = false)
    private String zipCode;

    // Mapeia o atributo para uma coluna específica na tabela.
    @Column(name = "delivery_complement")
    private String complement;
}
