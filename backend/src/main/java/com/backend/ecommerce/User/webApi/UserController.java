package com.backend.ecommerce.User.webApi;

import com.backend.ecommerce.User.business.requests.RegistrationDto;
import com.backend.ecommerce.User.business.responses.LoginResponseDTO;
import com.backend.ecommerce.User.business.service.AuthServices;
import com.backend.ecommerce.User.entities.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private AuthServices authenticationServices;

    @PostMapping("/register")
    public ApplicationUser register(@RequestBody RegistrationDto body){
        return authenticationServices.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody RegistrationDto body){
        return authenticationServices.loginUser(body.getUsername(), body.getPassword());
    }

}
