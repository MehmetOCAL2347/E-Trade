package com.backend.ecommerce.Product.entities.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of="id")
public class Product {

    private String id;
    private String name;
    private String sellerId;
    private String categoryId;
    private String code;
    private int count;
    private Boolean isActive;
    private Double starPoint;
    private double price;
    private PriceType priceType;
    private String bulletPointId;
    private String commentsId;
    private String imagesId;

}
