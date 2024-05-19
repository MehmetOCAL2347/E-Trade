package com.backend.ecommerce.User.business.manager;

import com.backend.ecommerce.User.business.service.TokenService;
import com.backend.ecommerce.User.entities.entity.User;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TokenManager implements TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private JwtDecoder jwtDecoder;

    private final long EXPIRATION_TIME = 60000; // 20 sn, 1 second = 1000 milliseconds
    private final long RESET_PASSWORD_TIME = 1200000; // 20 dk

    @Override
    public String generateJwt(Authentication auth) throws KeyLengthException {

        String scope;
        User user;
        String userId;
        JwtClaimsSet claims;

        scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        user = (User) auth.getPrincipal();
        userId = user.getId();

        Instant now = Instant.now();
        Instant expiration = now.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);

        claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(user.getUsername())
                .claim("id", userId)
                .claim("roles", scope)
                .claim("email", user.getEmail())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String generateJwt(User user) {

        String userId = user.getId();
        String userName = user.getUsername();
        String userEmail = user.getEmail();
        JwtClaimsSet claims;

        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant expiration = now.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);

        claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(userName)
                .claim("id", userId)
                .claim("roles", scope)
                .claim("email", userEmail)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String resetPasswordToken(User user) {

        String userId = user.getId();
        JwtClaimsSet claims;

        Instant now = Instant.now();
        Instant expiration = now.plus(RESET_PASSWORD_TIME, ChronoUnit.MILLIS);

        claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(expiration)
                .claim("id", userId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public String getUserIdFromJwt(String jwt) throws ParseException {
        JWTClaimsSet claims = getClaimSetFromJwt(jwt);
        return claims.getClaim("id").toString();
    }

    @Override
    public boolean isUserExpire(String jwt) throws ParseException {
        JWTClaimsSet claims = getClaimSetFromJwt(jwt);
        Date expirationDate = claims.getExpirationTime();
        Date now = Date.from(Instant.now());
        if (now.before(expirationDate)) { // Expire olmadı
            return false;
        } else { // Expire oldu
            return true;
        }
    }

    @Override
    public String getToken(String token) {
        String[] parts = token.split(" ");
        String splittedToken = "";

        if (parts.length == 2 && parts[0].equalsIgnoreCase("Bearer")) {
            splittedToken = parts[1];
        }
        return splittedToken;
    }

    public JWTClaimsSet getClaimSetFromJwt(String jwt) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(jwt);
        return signedJWT.getJWTClaimsSet();
    }


}
