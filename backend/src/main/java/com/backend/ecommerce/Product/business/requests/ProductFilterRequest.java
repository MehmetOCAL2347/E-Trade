package com.backend.ecommerce.Product.business.requests;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductFilterRequest {

    private List<String> categoryName;
    private Boolean isActive;
    private Double starPoint;
    private Double minPriceValue;
    private Double maxPriceValue;

}
