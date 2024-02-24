package com.backend.ecommerce.Product.entities.entity;


import lombok.*;

import java.util.List;

//@Document(collection = "product")
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
    private List<String> bulletPoints;
    private List<String> comments;
    private List<Image> images;

}
