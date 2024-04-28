package com.backend.ecommerce.Cart.webApi;

import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    // User'a ait sepet bilgisini getircez
    @GetMapping("/getAll") // For Admin only!!
    public Optional<Cart> getCart() {
        return cartService.getCart(1);
    }

    @PostMapping("/addNewProductToCart")
    public String addNewProductToCart(){
        return "addNewProductToCart";
    }

}
