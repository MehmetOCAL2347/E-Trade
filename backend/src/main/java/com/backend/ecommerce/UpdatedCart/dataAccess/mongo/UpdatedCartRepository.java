package com.backend.ecommerce.UpdatedCart.dataAccess.mongo;

import com.backend.ecommerce.UpdatedCart.entities.entity.UpdatedCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UpdatedCartRepository extends ReactiveMongoRepository<UpdatedCart, String> {
    Mono<UpdatedCart> findByOrderId(String orderId);
}
