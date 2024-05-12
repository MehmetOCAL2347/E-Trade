package com.backend.ecommerce.User.webApi;

import com.backend.ecommerce.User.business.requests.UserRequestDTO;
import com.backend.ecommerce.User.business.responses.UserResponseDTO;
import com.backend.ecommerce.User.business.service.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthServices authenticationServices;

    // TODO-5 UserDTO oluşturulmalı
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO body){
        return authenticationServices.registerUser(body);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO body){
        return authenticationServices.loginUser(body.getUsername(), body.getPassword());
    }

}
