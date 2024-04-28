package com.backend.ecommerce.Product.business.requests;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulletPointListRequest {
    private List<String> bulletPoints;
}
