package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.ImageList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageListRepositoryMongo extends MongoRepository<ImageList, String> {
}
