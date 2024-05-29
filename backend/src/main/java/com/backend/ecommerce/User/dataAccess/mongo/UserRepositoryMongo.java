package com.backend.ecommerce.User.dataAccess.mongo;

import com.backend.ecommerce.User.entities.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryMongo extends MongoRepository<User, String> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    /*Optional<User> existByUsername(String userName);*/
}
