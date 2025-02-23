package com.backend.ecommerce.UpdatedCart.business.rules;

import reactor.core.publisher.Mono;

public interface UpdatedCartRulesService {
    Mono<Void> isCartExist(String orderId);
}
