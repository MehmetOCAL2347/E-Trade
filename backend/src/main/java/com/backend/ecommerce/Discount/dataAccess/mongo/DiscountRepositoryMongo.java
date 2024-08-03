package com.backend.ecommerce.Discount.dataAccess.mongo;

import com.backend.ecommerce.Discount.entities.entity.Discount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepositoryMongo extends MongoRepository<Discount,String> {
    Optional<Discount> findByDiscountCode(String discountCode);
    boolean existsByDiscountCode(String discountCode);
}
