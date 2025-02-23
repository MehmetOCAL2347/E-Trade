package com.backend.ecommerce.Product.business.rules;

import com.backend.ecommerce.UpdatedCart.entities.entity.CartItem;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductRulesService {
    Mono<Void> areProductsExist(List<CartItem> cartItemList);
    void isProductExist(String productId);
}
