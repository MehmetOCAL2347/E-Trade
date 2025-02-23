package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.BulletPoints;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BulletPointsRepositoryMongo extends ReactiveMongoRepository<BulletPoints, String> {
}
