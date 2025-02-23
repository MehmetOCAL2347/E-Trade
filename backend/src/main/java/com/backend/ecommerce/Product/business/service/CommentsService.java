package com.backend.ecommerce.Product.business.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface CommentsService {
    Mono<String> saveComments(List<String> comments, String productId);
}
