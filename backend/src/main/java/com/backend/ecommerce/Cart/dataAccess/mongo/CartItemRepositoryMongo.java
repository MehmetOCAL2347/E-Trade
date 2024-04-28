package com.backend.ecommerce.Cart.dataAccess.mongo;

import com.backend.ecommerce.Cart.entities.entity.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepositoryMongo extends MongoRepository<CartItem, Integer> {
}
