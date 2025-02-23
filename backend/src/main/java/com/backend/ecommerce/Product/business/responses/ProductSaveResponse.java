package com.backend.ecommerce.Product.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductSaveResponse {
    private String createdProductCode;
}
