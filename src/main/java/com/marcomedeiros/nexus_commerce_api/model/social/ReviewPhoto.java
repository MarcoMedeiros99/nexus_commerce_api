package com.marcomedeiros.nexus_commerce_api.model.social;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_review_photo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhoto;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_review", nullable = false)
    private ProductReview review;
}