package com.backend.ecommerce.Category.business.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

    private String name;
    private String code;

}
