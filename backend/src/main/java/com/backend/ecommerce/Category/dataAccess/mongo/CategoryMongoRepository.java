package com.backend.ecommerce.Category.dataAccess.mongo;

import com.backend.ecommerce.Category.entities.entity.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface CategoryMongoRepository extends ReactiveMongoRepository<Category, String> {
    Mono<Category> findByName(String name);
}
