package com.backend.ecommerce.User.dataAccess.mongo;

import com.backend.ecommerce.User.entities.entity.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryMongo extends MongoRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String userName);
}
