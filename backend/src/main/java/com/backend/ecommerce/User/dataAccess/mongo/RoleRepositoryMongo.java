package com.backend.ecommerce.User.dataAccess.mongo;

import com.backend.ecommerce.User.entities.entity.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositoryMongo extends MongoRepository<Roles, Integer> {
    Optional<Roles> findByAuthority(String authority);
}
