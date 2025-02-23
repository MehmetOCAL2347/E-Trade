package com.backend.ecommerce.Order.business.rules;

import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import reactor.core.publisher.Mono;

public interface OrderRulesService {
    Mono<Void> checkEmptyAreas(CreateOrderRequest createOrderRequest);
}
