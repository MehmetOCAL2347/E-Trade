package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.BulletPoints;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BulletPointsRepositoryMongo extends MongoRepository<BulletPoints, String> {
}
