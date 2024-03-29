package com.backend.ecommerce.Product.business.responses;

import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ProductMainPageResponse {

    private String code;
    private String name;
    private int count;
    private Boolean isActive;
    private Double starPoint;
    private double price;
    private PriceType priceType;
    private String url;
    private String categoryName;

}
