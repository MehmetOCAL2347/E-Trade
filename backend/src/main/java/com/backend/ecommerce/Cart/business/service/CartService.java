package com.backend.ecommerce.Cart.business.service;

import com.backend.ecommerce.Cart.business.requests.AddToCartRequest;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CartService {
    /*Cart getCart(Integer id);*/
    String getCartId(Cart cart);
    Optional<Cart> getCart(String cartId);
    ResponseEntity<String> updateCart(Cart cart);
}
