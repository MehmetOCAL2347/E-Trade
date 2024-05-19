package com.backend.ecommerce.User.webApi;

import com.backend.ecommerce.User.business.requests.ForgotPasswordRequestDTO;
import com.backend.ecommerce.User.business.requests.NewPasswordDTO;
import com.backend.ecommerce.User.business.requests.UserLoginRequestDTO;
import com.backend.ecommerce.User.business.requests.UserRegisterRequestDTO;
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

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterRequestDTO body) {
        return authenticationServices.registerUser(body);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserLoginRequestDTO body) {
        return authenticationServices.loginUser(body);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestHeader(name = "Authorization") String token) {
        return authenticationServices.deleteUser(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserResponseDTO> logout(@RequestHeader(name = "Authorization") String token) {
        return authenticationServices.logout(token);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        return authenticationServices.forgotPassword(forgotPasswordRequestDTO.getEmail());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody NewPasswordDTO newPasswordDTO) {
        return authenticationServices.resetPassword(token, newPasswordDTO.getNewPassword());
    }

}
