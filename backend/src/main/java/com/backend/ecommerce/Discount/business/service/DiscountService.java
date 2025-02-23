package com.backend.ecommerce.Discount.business.service;

import com.backend.ecommerce.Discount.business.requests.DiscountRequest;
import com.backend.ecommerce.Discount.business.responses.DiscountResponse;
import reactor.core.publisher.Mono;

public interface DiscountService {

    Mono<DiscountResponse> getAndCheckDiscount(DiscountRequest discountRequest);
    Integer getDiscountValue(String discountCode);

}
