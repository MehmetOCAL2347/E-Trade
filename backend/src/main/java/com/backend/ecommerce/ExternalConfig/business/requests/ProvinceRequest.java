package com.backend.ecommerce.ExternalConfig.business.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceRequest {
    private String baseUrl;
    private String endPoint;
}
