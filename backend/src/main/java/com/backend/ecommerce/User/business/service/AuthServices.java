package com.backend.ecommerce.User.business.service;

import com.backend.ecommerce.User.business.requests.UserLoginRequestDTO;
import com.backend.ecommerce.User.business.requests.UserRegisterRequestDTO;
import com.backend.ecommerce.User.business.responses.UserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthServices {

    ResponseEntity<UserResponseDTO> registerUser(UserRegisterRequestDTO registrationDto);
    ResponseEntity<UserResponseDTO> loginUser(UserLoginRequestDTO loginDTO);
    ResponseEntity<UserResponseDTO> deleteUser(String token);
    ResponseEntity<UserResponseDTO> logout(String token);
    ResponseEntity<?> forgotPassword(String email);
    ResponseEntity<?> resetPassword(String token, String newPassword);
}
