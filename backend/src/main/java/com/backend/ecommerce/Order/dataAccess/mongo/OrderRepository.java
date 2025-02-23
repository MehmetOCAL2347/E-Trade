package com.backend.ecommerce.Order.dataAccess.mongo;

import com.backend.ecommerce.Order.entities.entity.OrderDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderDetail,String> {
    Mono<OrderDetail> findByOrderId(String orderId);
}
