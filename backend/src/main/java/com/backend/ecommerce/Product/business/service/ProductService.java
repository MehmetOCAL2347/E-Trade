package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductPriceResponse;
import com.backend.ecommerce.Product.entities.entity.Product;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface ProductService {

    Mono<ProductDetailPageResponse> getProductDetail(String code);
    Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest productFilterRequest);
    Optional<ProductPriceResponse> getProductPriceById(String id);
    Mono<Product> getProductWithIdDummy(String id);
    ResponseEntity<String> saveNewProduct(ProductSaveRequest productSaveRequest);

}
