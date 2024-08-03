package com.backend.ecommerce.Product.business.responses;

import com.backend.ecommerce.Product.entities.entity.PriceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductCartPageResponse {

    private String code;
    private String name;
    private String url;
    private double price;
    private PriceType priceType;
}
