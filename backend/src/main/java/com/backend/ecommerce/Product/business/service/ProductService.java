package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<ProductMainPageResponse> getAllProducts();

}
