package com.backend.ecommerce.User.business.service;

import com.backend.ecommerce.User.entities.entity.User;
import com.nimbusds.jose.KeyLengthException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.Date;

public interface TokenService {
    String generateJwt(Authentication auth) throws KeyLengthException;
    String generateJwt(User user);
    String resetPasswordToken(User user);
    String getUserIdFromJwt(String jwt) throws ParseException;
    boolean isUserExpire(String jwt) throws ParseException;
    String getToken(String token);
}
