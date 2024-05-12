package com.backend.ecommerce.Cart.dataAccess.mongo;

import com.backend.ecommerce.Cart.entities.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepositoryMongo extends MongoRepository<Cart, String> {
    /*Cart findByCartId(Integer cartId);*/
}
