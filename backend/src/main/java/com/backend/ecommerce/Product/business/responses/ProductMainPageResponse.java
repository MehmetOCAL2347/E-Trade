package com.backend.ecommerce.Product.business.responses;

import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductMainPageResponse {

    private String code;
    private String name;
    private int count;
    private Boolean isActive;
    private double starPoint;
    private double price;
    private PriceType priceType;
    private String url;
    private String categoryName;

}
