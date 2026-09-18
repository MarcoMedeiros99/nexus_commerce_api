package com.marcomedeiros.nexus_commerce_api.model.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank
    @Column(name = "delivery_street_address", nullable = false, length = 150)
    private String streetAddress;
    @NotBlank
    @Size(max = 10)
    @Column(name = "delivery_number", nullable = false)
    private String number;
    @NotBlank
    @Column(name = "delivery_city", nullable = false, length = 60)
    private String city;
    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "delivery_state", nullable = false)
    private String state;
    @NotBlank
    @Column(name = "delivery_neighborhood", nullable = false)
    private String neighborhood;
    @Pattern(regexp = "\\d{5}-\\d{3}")
    @Column(name = "delivery_zip_code", nullable = false)
    private String zipCode;
    @Column(name = "delivery_complement")
    private String complement;
}
