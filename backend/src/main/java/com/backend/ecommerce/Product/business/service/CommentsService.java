package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.entities.entity.Comments;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommentsService {
    Mono<String> saveComments(List<String> comments, String productId);
    Mono<Comments> findComments(String productCode);
}
