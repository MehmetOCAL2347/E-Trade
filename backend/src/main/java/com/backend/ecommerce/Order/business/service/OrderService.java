package com.backend.ecommerce.Order.business.service;

import com.backend.ecommerce.Order.business.requests.CreateOrderRequest;
import com.backend.ecommerce.Order.business.responses.CreateOrderResponse;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface OrderService {
    //ResponseEntity<CreateOrderResponse> createOrder(CreateOrderRequest createOrderRequest);
    Mono<ResponseEntity<CreateOrderResponse>> createOrder(CreateOrderRequest createOrderRequest);
}
