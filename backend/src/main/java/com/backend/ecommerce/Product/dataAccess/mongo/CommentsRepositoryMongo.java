package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.Comments;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CommentsRepositoryMongo extends ReactiveMongoRepository<Comments, String> {
    Mono<Comments> findByProductId(String productCode);
}
