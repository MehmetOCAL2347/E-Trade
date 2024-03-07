package com.backend.ecommerce.User.business.service;

import com.backend.ecommerce.User.business.responses.LoginResponseDTO;
import com.backend.ecommerce.User.entities.entity.ApplicationUser;

public interface AuthServices {

    // Login method
    // Register method

    ApplicationUser registerUser(String username, String password);
    LoginResponseDTO loginUser(String username, String password);

    // TODO
    // Return registered DTO object
    // Return login DTO object

}
