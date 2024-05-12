package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepositoryMongo extends MongoRepository<Product, Integer> {
    Product findById(String id);
}
