package com.backend.ecommerce.User.business.service;

import com.backend.ecommerce.Cart.business.requests.AddToCartRequest;
import com.backend.ecommerce.User.business.requests.UserRequestDTO;
import com.backend.ecommerce.User.business.responses.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface AuthServices {

    ResponseEntity<UserResponseDTO> registerUser(UserRequestDTO registrationDto);
    ResponseEntity<UserResponseDTO> loginUser(String username, String password);
    ResponseEntity<String> addProductToCart(String token, AddToCartRequest addToCartRequest) throws ParseException;
}
