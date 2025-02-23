package com.backend.ecommerce.UpdatedCart.business.service;

import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import reactor.core.publisher.Mono;

public interface UpdatedCartService {
    Mono<Void> saveNewCart(CreateOrderRequest createOrderRequest, String orderId);
}
