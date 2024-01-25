package com.backend.ecommerce.Product.entities.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

//@Document(collection = "product")
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
    private BigDecimal price;
    private PriceType priceType;
    private List<String> description;
    private List<String> comments;
    private List<Image> images;

}
