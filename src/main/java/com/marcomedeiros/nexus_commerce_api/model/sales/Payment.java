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

@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Payment implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @Column(name = "access_code", nullable = false, unique = true, updatable = false, length = 15)
    private String accessCode;

    @Convert(converter = PaymentMethodConverter.class)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private LocalDateTime paymentDateTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @PrePersist
    private void generateAccessCode() {
        if (this.accessCode == null) {
            String randomHash = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
            this.accessCode = "#PAY-" + randomHash;
        }
        if (this.paymentMethod == null) {
            this.paymentMethod = PaymentMethod.CREDIT_CARD;
        }
        if (this.paymentDateTime == null){
            this.paymentDateTime = LocalDateTime.now();
        }
    }

}
