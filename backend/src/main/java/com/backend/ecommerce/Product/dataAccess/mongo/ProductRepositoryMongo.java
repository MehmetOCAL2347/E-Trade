package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRepositoryMongo extends ReactiveMongoRepository<Product, Integer> {
    Mono<Product> findById(String id);
    Flux<Product> findByCodeIn(List<String> codes);
    Mono<Product> findByCode(String code);
}
