package com.backend.ecommerce.Product.entities.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class Comments {

    @Id
    private String id;
    private List<String> comments;
    private String productId;
    // userId'de tutulmalı, commenti kimin yaptığını bilmek gerekir
    // Yıldız puanı da double olarak tutulmalı
}
