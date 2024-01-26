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
    private String brandId;
    private String code;
    private int count;  // Stok sayısı
    private boolean isActive;
    private double starPoint;
    private double price;
    private PriceType priceType;
    private List<String> description;
    private List<String> comments;
    private List<Image> images;

}
