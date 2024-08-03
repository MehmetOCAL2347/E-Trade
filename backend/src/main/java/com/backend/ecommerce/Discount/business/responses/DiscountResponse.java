package com.backend.ecommerce.Discount.business.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DiscountResponse {
    private int discountValue;
}
