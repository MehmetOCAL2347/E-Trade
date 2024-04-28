package com.backend.ecommerce.Cart.entities.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/*@Entity
@Document(collection = "cart_items")
@AllArgsConstructor
@NoArgsConstructor*/
@Entity
@Document(collection = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    private Integer cartItemId;
    private String name;
    //private int productId; // Ürün id'si
    //private int count; // Ürün adet bilgisi
}
