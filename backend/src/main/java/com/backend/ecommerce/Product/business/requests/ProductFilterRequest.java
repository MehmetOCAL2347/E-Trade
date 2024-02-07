package com.backend.ecommerce.Product.business.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFilterRequest {

    private String categoryName;
    private Boolean isActive;

}
