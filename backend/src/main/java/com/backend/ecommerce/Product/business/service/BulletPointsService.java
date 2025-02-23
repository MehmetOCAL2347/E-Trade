package com.backend.ecommerce.Product.business.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface BulletPointsService {
    //String saveBulletPoints(List<String> bulletPoints, String productId);
    Mono<String> saveBulletPoints(List<String> bulletPoints, String productCode);
}
