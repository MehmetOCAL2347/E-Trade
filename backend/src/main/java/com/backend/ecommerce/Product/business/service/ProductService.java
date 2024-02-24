package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    /*Flux<ProductMainPageResponse> getAllProducts();*/
    Mono<ProductDetailPageResponse> getProductDetail(String code);
    Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest productFilterRequest);

}
