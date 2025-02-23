package com.backend.ecommerce.Product.business.service;

import com.backend.ecommerce.Product.business.requests.ProductCartPageRequest;
import com.backend.ecommerce.Product.business.requests.ProductFilterRequest;
import com.backend.ecommerce.Product.business.requests.ProductSaveRequest;
import com.backend.ecommerce.Product.business.responses.ProductCartPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductDetailPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductMainPageResponse;
import com.backend.ecommerce.Product.business.responses.ProductSaveResponse;
import com.backend.ecommerce.Product.entities.entity.Product;
import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {

    Mono<ProductDetailPageResponse> getProductDetail(String code);
    Flux<ProductMainPageResponse> getProductsWithParams(ProductFilterRequest productFilterRequest);
    Flux<ProductCartPageResponse> getCartDetails(ProductCartPageRequest productCartPageRequest);
    Mono<Double> calculateUpdatedCartTotalPrice(List<CartItem> cartItemList);
    Mono<Double> calculateTotalPriceForCart(List<CartItem> cartItemList);  // TODO - Nerede kullanılıyor?
    Mono<ProductSaveResponse> saveNewProduct(ProductSaveRequest productSaveRequest);

}
