package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.entities.entity.Product;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<ProductDetailPageResponse> getProductDetail(String code);
    Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest productFilterRequest);
    Double calculateTotalPriceForCart(List<String> productIds);
    Mono<Product> getProductWithIdDummy(String id);
    ResponseEntity<String> saveNewProduct(ProductSaveRequest productSaveRequest);

}
