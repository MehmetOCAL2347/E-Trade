package com.backend.ecommerce.Cart.entities.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "carts")
@Data
@EqualsAndHashCode(of="id")
public class Cart{

    private String id;
    private List<String> productIds;
    private Double totalPrice;
    private int totalCount;

    public Cart() {
        this.productIds = new ArrayList<>();
        this.totalPrice = 0.0;
        this.totalCount = 0;
    }
}
