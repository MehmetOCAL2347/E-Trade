package com.backend.ecommerce.Cart.business.manager;

import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.dataAccess.mongo.CartRepositoryMongo;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartManager implements CartService {

    @Autowired
    private CartRepositoryMongo cartRepository;

    @Override
    public String getCartId(Cart cart) {
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    @Override
    public Optional<Cart> getCart(String cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return Optional.of(cart);
    }

    @Override
    public ResponseEntity<String> updateCart(Cart cart) {
        cartRepository.save(cart);
        return ResponseEntity.ok("Cart Updated");
    }

    /*@Override
    public Cart getCart(Integer id) {
        return cartRepository.findByCartId(id);
    }*/
}
