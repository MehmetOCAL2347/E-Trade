package com.backend.ecommerce.User.business.manager;

import com.backend.ecommerce.Cart.business.requests.AddToCartRequest;
import com.backend.ecommerce.Cart.business.service.CartService;
import com.backend.ecommerce.Cart.entities.entity.Cart;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.UserFailedException;
import com.backend.ecommerce.Core.ErrorHandling.RuntimeExceptions.TokenCreationException;
import com.backend.ecommerce.Core.Mail.Mail;
import com.backend.ecommerce.Product.business.service.ProductService;
import com.backend.ecommerce.User.business.requests.UserLoginRequestDTO;
import com.backend.ecommerce.User.business.requests.UserRegisterRequestDTO;
import com.backend.ecommerce.User.business.responses.UserResponseDTO;
import com.backend.ecommerce.User.business.rules.UserRulesService;
import com.backend.ecommerce.User.business.service.AuthServices;
import com.backend.ecommerce.User.business.service.TokenService;
import com.backend.ecommerce.User.dataAccess.mongo.RoleRepositoryMongo;
import com.backend.ecommerce.User.dataAccess.mongo.UserRepositoryMongo;
import com.backend.ecommerce.User.entities.entity.Roles;
import com.backend.ecommerce.User.entities.entity.User;
import com.nimbusds.jose.KeyLengthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthManager implements AuthServices {

    @Autowired
    private final UserRepositoryMongo userRepository;
    @Autowired
    private final RoleRepositoryMongo roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final TokenService tokenService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Mail mail;
    @Autowired
    private UserRulesService userRulesService;

    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRegisterRequestDTO registrationDto) {

        String email;
        String encodedPassword;
        Roles userRole;
        Set<Roles> authorities = new HashSet<>();;

        encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        userRole = roleRepository.findByAuthority("USER").get();

        authorities.add(userRole);
        email = registrationDto.getEmail();

        // Business Rules
        userRulesService.isEmailFormatCorrect(email);
        userRulesService.isUserExist(email);

        User savedUser = userRepository.save(
                User.builder()
                        .email(registrationDto.getEmail())
                        .username(registrationDto.getEmail()) // Username == userMail
                        .password(encodedPassword)
                        .cartId(cartService.getCartId(new Cart()))
                        .authorities(authorities)
                        .resetToken("")
                        .build()
        );

        try {
            String token = tokenService.generateJwt(savedUser);
            return ResponseEntity.ok(new UserResponseDTO(token));
        }catch (Exception e){
            throw new UserFailedException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "User could not been created");
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> loginUser(UserLoginRequestDTO loginDTO) {

        // Business Rules
        userRulesService.isEmailFormatCorrect(loginDTO.getEmail());

        String jwt;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            jwt = tokenService.generateJwt(auth);

            return ResponseEntity.ok(new UserResponseDTO(jwt));

        } catch (AuthenticationException e) {
            throw new UserFailedException(HttpStatus.UNAUTHORIZED.value(), "Invalid email or password");
        } catch (KeyLengthException e) {
            throw new TokenCreationException("Token could not been created correctly");
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> deleteUser(String token) {

        String userId;
        String cartId;
        String splittedToken = tokenService.getToken(token);

        // Business Rule

        try {
            if (!tokenService.isUserExpire(splittedToken)) {
                userId = tokenService.getUserIdFromJwt(splittedToken);
                cartId = userRepository.findById(userId).get().getCartId();

                userRepository.deleteById(userId);
                cartService.deletById(cartId);
                return ResponseEntity.ok(new UserResponseDTO());
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseDTO());
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponseDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserResponseDTO());
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> logout(String token) {

        String splittedToken = tokenService.getToken(token);
        try {
            if (!tokenService.isUserExpire(splittedToken)) {
                return ResponseEntity.ok(new UserResponseDTO());
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserResponseDTO());
        }
    }

    @Override
    public ResponseEntity<?> forgotPassword(String email) {
        String defaultToken;
        String resetLink = "http://localhost:8080/reset-password?token=";

        try {
            User user = userRepository.findByEmail(email).get();
            defaultToken = tokenService.resetPasswordToken(user);

            if (user != null) {
                resetLink += defaultToken;
                user.setResetToken(defaultToken);
                userRepository.save(user);
                mail.sendEmail("ocalmehmet.2347@gmail.com", "Reset Password", resetLink);
                return ResponseEntity.ok("Şifre sıfırlama için mail adresinizi kontrol ediniz");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Bulunamadı");
    }

    @Override
    public ResponseEntity<?> resetPassword(String token, String newPassword) {

        String encodedPassword;
        String userResetToken;
        String userId;
        User user;

        try {
            if(!tokenService.isUserExpire(token)){

                userId = tokenService.getUserIdFromJwt(token);
                user = userRepository.findById(userId).get();
                userResetToken = user.getResetToken();

                if (userResetToken.equals(token)){
                    encodedPassword = passwordEncoder.encode(newPassword);
                    user.setPassword(encodedPassword);
                    user.setResetToken("");
                    userRepository.save(user);
                    return ResponseEntity.ok("Şifre Başarılı Şekilde Güncellendi");
                }
                else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tokenler uyuşmadı");
                }

            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expire oldu");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<String> addProductToCart(String token, AddToCartRequest addToCartRequest) throws ParseException {

        String userId;
        String cartId;
        Cart cart;
        String splittedToken = tokenService.getToken(token);

        if (!tokenService.isUserExpire(splittedToken)) {
            userId = tokenService.getUserIdFromJwt(splittedToken);
            cartId = userRepository.findById(userId).get().getCartId();
            cart = cartService.getCart(cartId).get();

            // update methodu ile de yapılabilir mi? service DB'ye eriştiğinde findAndUpdate() gibi bir method olabilir mongoda
            cart.setProductIds(addToCartRequest.getProductIds());
            cart.setTotalCount(addToCartRequest.getProductIds().size());
            cart.setTotalPrice(productService.calculateTotalPriceForCart(addToCartRequest.getProductIds()));

            cartService.updateCart(cart);
            return ResponseEntity.ok("Products Added To Cart");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Id bulunamadı");
        }
    }
}
