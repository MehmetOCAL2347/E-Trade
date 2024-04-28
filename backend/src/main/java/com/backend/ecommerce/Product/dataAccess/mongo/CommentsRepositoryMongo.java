package com.backend.ecommerce.Product.dataAccess.mongo;

import com.backend.ecommerce.Product.entities.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepositoryMongo extends MongoRepository<Comments, String> {
}
