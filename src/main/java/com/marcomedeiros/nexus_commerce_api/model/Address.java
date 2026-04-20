package com.marcomedeiros.nexus_commerce_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAddress;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String streetAddress;

    @NotBlank
    @Size(max = 10)
    private String number;

    @NotBlank
    @Column(nullable = false, length = 60)
    private String city;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(nullable = false)
    private String state;

    @Pattern(regexp = "\\d{5}-\\d{3}")
    @Column(nullable = false)
    private String zipCode; // CEP

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user") // FK
    private User user;
}