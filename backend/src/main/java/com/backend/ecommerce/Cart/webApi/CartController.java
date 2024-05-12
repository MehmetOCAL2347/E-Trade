package com.backend.ecommerce.Cart.webApi;

import com.backend.ecommerce.Cart.business.requests.AddToCartRequest;
import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import com.backend.ecommerce.User.business.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthServices authServices;

    // User'a ait sepet bilgisini getircez
    /*@GetMapping("/getAll") // For Admin only!!
    public Cart getCart() {
        return cartService.getCart(1);
    }*/

    @PostMapping("/addNewProductToCart")
    public ResponseEntity<String> addNewProductToCart(@RequestHeader(name = "Authorization") String token, @RequestBody AddToCartRequest addToCartRequest) throws ParseException {
        return authServices.addProductToCart(token, addToCartRequest);
    }

}
