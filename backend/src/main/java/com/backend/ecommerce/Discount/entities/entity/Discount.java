package com.backend.ecommerce.Discount.entities.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class Discount {
    private String id;
    private String discountCode;
    private int discountValue; // % olarak düşünülmeli
    private boolean isAlive; // Kullanılcak mı
}
