package com.backend.ecommerce.Cart.business.service;

import com.backend.ecommerce.Cart.entities.entity.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> getCart(Integer id);
}
